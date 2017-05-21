package com.gplearning.gplearning.Controllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gplearning.gplearning.DAO.App;
import com.gplearning.gplearning.DAO.UsuarioDAO;
import com.gplearning.gplearning.Enums.PapelUsuario;
import com.gplearning.gplearning.Models.DaoSession;
import com.gplearning.gplearning.Models.LoginRole;
import com.gplearning.gplearning.Models.Pessoa;
import com.gplearning.gplearning.R;
import com.gplearning.gplearning.Utils.MetodosPublicos;
import com.gplearning.gplearning.Utils.Sincronizacao;

import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;


    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    private UsuarioDAO userDAO;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    // private View mProgressDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupActionBar();


//        SharedPreferences shared = getSharedPreferences("login",MODE_PRIVATE);
//        String string_temp = shared.getString("user",null);
//        if(string_temp!=null){
//            Intent intent = new Intent(this, NivelAcessoActivity.class);
//            startActivity(intent);
//        }


        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        //  populateAutoComplete();

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        //  mProgressDesc = findViewById(R.id.login_progress_desc);
    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    //    private void populateAutoComplete() {
//        if (!mayRequestContacts()) {
//            return;
//        }
//
//        getLoaderManager().initLoader(0, null, this);
//    }

//    private boolean mayRequestContacts() {
//        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
//            return true;
//        }
//        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
//            return true;
//        }
//        if (shouldShowRequestPermissionRationale(READ_CONTACTS)) {
////            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
////                    .setAction(android.R.string.ok, new View.OnClickListener() {
////                        @Override
////                        @TargetApi(Build.VERSION_CODES.M)
////                        public void onClick(View v) {
////                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
////                        }
////                    });
//        } else {
//            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
//        }
//        return false;
//    }

//    /**
//     * Callback received when a permissions request has been completed.
//     */
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
//                                           @NonNull int[] grantResults) {
//        if (requestCode == REQUEST_READ_CONTACTS) {
//            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                populateAutoComplete();
//            }
//        }
//    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            //    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (!MetodosPublicos.IsConnected(this)) {
            cancel = true;
            Snackbar snackbar = Snackbar
                    .make(findViewById(R.id.email_login_form), getString(R.string.synchronization_error_connect), Snackbar.LENGTH_SHORT);
            snackbar.show();
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 2;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Pessoa> {

        private final String email;
        private final String password;
        private boolean admim = false;

        UserLoginTask(String email, String password) {
            this.email = email;
            this.password = password;
        }

        @Override
        protected Pessoa doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            //   try {

            // Simulate network access.
            // Thread.sleep(2000);
            DaoSession session = ((App) LoginActivity.this.getApplication()).getDaoSession();
            userDAO = new UsuarioDAO();
            try {
                Pessoa pessoa = userDAO.Login(session, email, password);
                if (pessoa != null) {
                    String token = pessoa.getLogin() != null ? pessoa.getLogin().getToken() : "";
                    MetodosPublicos.SalvaSessao(LoginActivity.this, pessoa.get_id(), pessoa.getNome(), email, pessoa.getId(), token);
                    if (pessoa.getImagem() != null && pessoa.getImagem().length > 0) {
                        MetodosPublicos.SaveImageUser(LoginActivity.this, pessoa.get_id(), pessoa.getImagem());
                    }

                    //sincroniza APP
                    Sincronizacao.SincronizaApp(LoginActivity.this, PapelUsuario.user);
                    List<LoginRole> lsLoginRoles = pessoa.getLogin() != null ? pessoa.getLogin().getLoginRoles() : null;
                    if (lsLoginRoles != null) {
                        for (LoginRole lg : lsLoginRoles) {
                            if (lg.getRole() == PapelUsuario.admin) {
                                admim = true;
                                Sincronizacao.SincronizaApp(LoginActivity.this, PapelUsuario.admin);
                                break;
                            }
                        }
                    }
                    return pessoa;
                }
                //  return new Pessoa();
            } catch (Exception e) {
                MetodosPublicos.Log("ERROR LOGIN", e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(final Pessoa user) {
            mAuthTask = null;
            showProgress(false);

            if (user != null) {

                if (user.getId() > 0) {

                    Intent intent;
                    if (admim) {
                        MetodosPublicos.SalvaModoAdmin(LoginActivity.this, true);
                        intent = new Intent(LoginActivity.this, NivelAcessoActivity.class);
                    } else {
                        MetodosPublicos.SalvaModoAcessoAluno(LoginActivity.this);
                        intent = new Intent(LoginActivity.this, MainActivity.class);
                    }
                    startActivityForResult(intent, RESULT_OK);
                    finish();
                    overridePendingTransition(R.animator.push_left_in, R.animator.push_left_out);
                } else {
                    mPasswordView.setError(getString(R.string.user_not_found));
                    mPasswordView.requestFocus();
                }
            } else {
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.email_login_form), getString(R.string.synchronization_error_connect), Snackbar.LENGTH_SHORT);
                snackbar.setDuration(10000);
                snackbar.show();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}

