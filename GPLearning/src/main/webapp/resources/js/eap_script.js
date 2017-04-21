$(function () {
    $('#eapModal').on('hidden.bs.modal', function (e) {
        $('body').removeClass('modal-open');
        ReloadNumbers();
    });
    $('#eapModal').on('shown.bs.modal', function (e) {
        $('body').addClass('modal-open');
        $('#eapModal [name=nome]').focus();
    });
});
$(document).on('click', '.eapEdit', function () {
    var eap = $(this).parents('.eap:eq(0)');
    var ordem = eap.find('[name=ordem]').val();
    eap.find('[name]').each(function (index, item) {
        var name = $(item).attr('name');
        var value = $(item).val();
        $('#eapModal [name="' + name + '"]').val(value);
    });
    $('#eapModal .modal-title').html('EAP - ' + ordem);
    if (ordem == '1')
        $('#eapModal .deletaEAP').hide();
    else
        $('#eapModal .deletaEAP').show();
    $('#eapModal').modal('show');
});

$(document).on('click', '#eapModal .salvaEAP', function () {
    var id = parseInt($('#eapModal [name=id]').val()) || 0;
    var pai_id = parseInt($('#eapModal [name="pai.id"]').val()) || 0;
    var eap = $('.eap [name=id][value="' + id + '"]').parents('.eap:eq()');
    if (id === 0)
    {
        eap = $('.HtmlExample .eap_item').clone();
        $('#eapModal [name=id]').val(number());
        $('[name="id"][value="' + pai_id + '"]').parents('.eap_item:eq(0)').find('.eap_pai:eq(0)').append(eap);
    }
    $('#eapModal [name]').each(function (index, item) {
        var name = $(item).attr('name');
        var value = $(item).val();
        eap.find('[name="' + name + '"]').val(value);
    });
    eap.find('.eap_nome').html($('#eapModal [name=nome]').val());
    Salvar();
});

$(document).on('click', '#eapModal .deletaEAP', function () {
    var id = parseInt($('#eapModal').find('[name=id]').val()) || 0;
    if (id > 0) {
        $.ajax({
            type: 'POST',
            url: '/GPLearning/api/eap/excluir',
            data: {eap_id: id},
            success: function (responser) {
                if (responser) {
                    var item = $('.eap [name=id][value="' + id + '"]').parents('.eap_item:eq(0)');
                    item.remove();
                    ReloadNumbers();
                    $('#eapModal').modal('hide');
                }
            }, error: function (error) {}
        });
    }
});

$(document).on('click', '.eapAdd', function () {
    var eap = $(this).parents('.eap:eq(0)');
    newEAP(eap);
//    var item = $(this).parents('.eap_item:eq(0)');
//    var html = $('.HtmlExample .eap_item').clone();
//    $(item).find('.eap_pai:eq(0)').append(html);
//    ReloadNumbers();
});

function CalculaLargura() {
    var pacotes = $('.eap_list .eap_column');
    pacotes.each(function (index, item) {
        var pais = $(item).find('.eap_pai:not(:empty)').length;
        $(item).children('.eap').css('marginRight', (pais * 15) + 'px');
    });
//    var length = pacotes.length;
//    var wid = 0;
//    if (length < 5)
//        wid = 19;
//    else
//        wid = 100 / length;
//    wid = Math.floor(wid);
//    pacotes.css('width', wid + '%');
}

function ReloadNumbers() {
    LoadIndex($('.eap_pai:eq(0)'), '');
    $('.eap_item.eap_column').removeClass('eap_column');
    $('.eap_list .eap_pai:eq(1) > .eap_item').addClass('eap_column');
    CalculaLargura();
}

function LoadIndex(pai, v_i) {
    $(pai).children('.eap_item').each(function (index, item) {
        var index_label = v_i + (index + 1);
        $(item).children('.eap').find('.eap_number').html(index_label);
        $(item).children('.eap').find('[name=ordem]').val(index_label);
        LoadIndex($(item).children('.eap_pai'), index_label + '.');
    });
}

function Salvar() {
    var form = $('#eapModal [name]');
    $.ajax({
        type: 'POST',
        url: '/GPLearning/api/eap/salvar',
        data: form.serialize(),
        success: function (responser) {
            if (responser && responser > 0) {
                $('#eapModal').modal('hide');
            }
        }, error: function (error) {}
    });
}

function loadEAP() {
    var id = parseInt("1") || 0;
    if (id > 0) {
        $.ajax({
            type: 'GET',
            url: '/GPLearning/api/eap/index/' + id,
            //data: {pro_id: 1},
            success: function (responser) {
                $('.eap_list').html('');
                if (responser && responser.length > 0) {

                } else {
                    var html = $('.HtmlExample .eap_pai').clone();
                    var pro_id = parseInt($('#pro_id').val()) || 0;//number()
                    var pro_nome = $('#pro_nome').val();
                    pro_nome = pro_nome && $.trim(pro_nome) != '' ? pro_nome : 'Nome do Projeto';
                    html.find('[name=id]').val(pro_id);
                    html.find('[name=nome]').val(pro_nome);
                    html.find('.eap_nome').html(pro_nome);
                    $('.eap_list').append(html);
                    ReloadNumbers();
                }
            },
            error: function (error) {}
        });
    }
}
$(function () {
    loadEAP();
});

function newEAP(eap) {
    var ordem = $(eap).find('[name=ordem]').val();
    var childrens = $(eap).next('.eap_pai').children('.eap_item').length;
    ordem = ordem + '.' + (childrens + 1);
    $('#eapModal [name]').val('');
    $('#eapModal [name="pai.id"]').val($(eap).find('[name=id]').val());
    $('#eapModal [name=ordem]').val(ordem);
    $('#eapModal .modal-title').html('EAP - ' + ordem);
    $('#eapModal .deletaEAP').hide();
    $('#eapModal').modal('show');
}

function number() {
    return Math.floor((1 + Math.random()) * 0x10000);
}