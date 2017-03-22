$(function () {
    $('#eapModal').on('hidden.bs.modal', function (e) {
        $('body').removeClass('modal-open');
    });
    $('#eapModal').on('show.bs.modal', function (e) {
        $('body').addClass('modal-open');
    });
});
$(document).on('click', '.eapEdit', function () {
    var item = $(this).parents('.block_item');
    $('#eapModal').find('[name=id]').val(item.find('[name=id]').val());
    $('#eapModal').find('[name=ordem]').val(item.find('[name=ordem]').val());
    $('#eapModal').find('[name=nome]').val(item.find('[name=nome]').val());
    $('#eapModal').find('[name=descricao]').val(item.find('[name=descricao]').val());
    $('#eapModal').find('[name=inicio]').val(item.find('[name=inicio]').val());
    $('#eapModal').find('[name=termino]').val(item.find('[name=termino]').val());
    $('#eapModal').find('[name=valor]').val(item.find('[name=valor]').val());
    $('#eapModal').modal('show');
});

$(document).on('click', '#eapModal .salvaEAP', function () {
    var ordem = $('#eapModal').find('[name=ordem]').val();
    var item = $('.block_row [name=ordem][value="' + ordem + '"]').parents('.block_item');
    item.find('.block_nome').html($('#eapModal').find('[name=nome]').val());
    item.find('[name=nome]').val($('#eapModal').find('[name=nome]').val());
    item.find('[name=descricao]').val($('#eapModal').find('[name=descricao]').val());
    item.find('[name=inicio]').val($('#eapModal').find('[name=inicio]').val());
    item.find('[name=termino]').val($('#eapModal').find('[name=termino]').val());
    item.find('[name=valor]').val($('#eapModal').find('[name=valor]').val());
    $('#eapModal').modal('hide');
});

$(document).on('click', '#eapModal .deletaEAP', function () {
    var ordem = $('#eapModal').find('[name=ordem]').val();
    var sub_pacote = $('.block_row [name=ordem][value="' + ordem + '"]').parents('.block_item');
    var pacote = sub_pacote.parents('.block_column');
    if (pacote.find('.block_item').length == 1) {
        pacote.remove();
        CalculaLargura()
    } else
        sub_pacote.remove();
    ReloadNumbers();
    $('#eapModal').modal('hide');
});

$(document).on('click', '.block_all .addPacote', function () {
    var html = $('.HtmlExample .block_column').clone();
    $('.block_all .block_row').append(html);
    CalculaLargura();
    ReloadNumbers();
});

$(document).on('click', '.eapAdd', function () {
    var item = $(this).parents('.block_item');
    var html = $('.HtmlExample .block_column .block_item').clone();
    $(html).insertAfter(item);
    ReloadNumbers();
});

$(document).on('click', '.eapDelete', function () {
    var sub_pacote = $(this).parents('.block_item');
    var pacote = sub_pacote.parents('.block_column');
    if (pacote.find('.block_item').length == 1) {
        pacote.remove();
        CalculaLargura()
    } else
        sub_pacote.remove();
    ReloadNumbers();
});

function CalculaLargura() {
    var pacotes = $('.block_all .block_row .block_column');
    var length = pacotes.length;
    var wid = 0;
    if (length < 5)
        wid = 19;
    else
        wid = 100 / length;
    wid = Math.floor(wid);
    pacotes.css('width', wid + '%');
}

function ReloadNumbers() {
    var column = $('.block_row .block_column');
    $(column).each(function (index1, item1) {
        $(item1).find('.block_item').each(function (index2, item2) {
            var index_label = (index1 + 1) + '.' + (index2 + 1);
            $(item2).find('.eapNumber').html(index_label);
            $(item2).find('[name=ordem]').val(index_label);
        });
    });
}


function Salvar() {
    $.ajax({
        type: 'POST',
        url: '/GPLearning/api/eap/salvar',
        success: function (responser) {

        }, error: function (error) {}
    });
}

function loadEAP() {
    $.ajax({
        type: 'GET',
        url: '/GPLearning/api/eap/index',
        data: {pro_id: 1},
        success: function (responser) {

        },
        error: function (error) {}
    });
}