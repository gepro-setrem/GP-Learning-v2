
$(document).on('click', '.eap_list .eapDetail', function () {
    var eap = $(this).parents('.eap:eq(0)');
    var ordem = eap.find('[name=ordem]').val();
    var nome = eap.find('[name=nome]').val();
    var descricao = eap.find('[name=descricao]').val();
    var inicio = eap.find('[name=inicio]').val();
    var termino = eap.find('[name=termino]').val();
    var valor = eap.find('[name=valor]').val();

//    $('#eapDetailModal .modal-title').html('EAP - ' + ordem);
    $('#eapDetailModal .modal-title').html(nome);
//    $('#eapDetailModal .nome').html(nome);
    $('#eapDetailModal .descricao').html(descricao);
    $('#eapDetailModal .inicio').html(inicio);
    $('#eapDetailModal .termino').html(termino);
    $('#eapDetailModal .valor').html(valor);

    $('#eapDetailModal').modal('show');
});