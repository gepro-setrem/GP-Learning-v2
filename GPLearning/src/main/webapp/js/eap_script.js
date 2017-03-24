$(function () {
    $('#eapModal').on('hidden.bs.modal', function (e) {
        $('body').removeClass('modal-open');
    });
    $('#eapModal').on('show.bs.modal', function (e) {
        $('body').addClass('modal-open');
    });
});
$(document).on('click', '.eapEdit', function () {
    var item = $(this).parents('.eap:eq(0)');
    var ordem = item.find('[name=ordem]').val();
    $('#eapModal').find('[name=id]').val(item.find('[name=id]').val());
    $('#eapModal').find('[name=ordem]').val(ordem);
    $('#eapModal').find('[name=nome]').val(item.find('[name=nome]').val());
    $('#eapModal').find('[name=descricao]').val(item.find('[name=descricao]').val());
    $('#eapModal').find('[name=inicio]').val(item.find('[name=inicio]').val());
    $('#eapModal').find('[name=termino]').val(item.find('[name=termino]').val());
    $('#eapModal').find('[name=valor]').val(item.find('[name=valor]').val());
    $('#eapModal .modal-title').html('EAP - ' + ordem);
    if (ordem == '1')
        $('#eapModal .deletaEAP').hide();
    else
        $('#eapModal .deletaEAP').show();
    $('#eapModal').modal('show');
});

$(document).on('click', '#eapModal .salvaEAP', function () {
    var ordem = $('#eapModal').find('[name=ordem]').val();
    var item = $('.eap [name=ordem][value="' + ordem + '"]').parents('.eap:eq()');
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
    var item = $('.eap [name=ordem][value="' + ordem + '"]').parents('.eap_item:eq(0)');
    item.remove();
    ReloadNumbers();
    $('#eapModal').modal('hide');
});


$(document).on('click', '.add_irmao', function () {
    var item = $(this).parents('.eap_item:eq(0)');
    var html = $('.HtmlExample .eap_item').clone();
    $(html).insertAfter(item);
    ReloadNumbers();
});

$(document).on('click', '.add_filho', function () {
    var item = $(this).parents('.eap_item:eq(0)');
    var html = $('.HtmlExample .eap_item').clone();
    $(item).find('.eap_pai:eq(0)').append(html);
    ReloadNumbers();
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
        $(item).children('.eap').find('.eapNumber').html(index_label);
        $(item).children('.eap').find('[name=ordem]').val(index_label);
        LoadIndex($(item).children('.eap_pai'), index_label + '.');
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
            $('.eap_list').html('');
            if (responser && responser.length > 0) {

            } else {
                var html = $('.HtmlExample .eap_pai').clone();
                html.find('.add_irmao').remove();
                $('.eap_list').append(html);
                ReloadNumbers();
            }
        },
        error: function (error) {}
    });
}
$(function () {
    loadEAP();
});