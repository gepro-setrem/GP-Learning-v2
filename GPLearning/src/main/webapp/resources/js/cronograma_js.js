$(function () {
    loadTarefa();

    $('#tarefaModal').on('hidden.bs.modal', function (e) {
        $('body').removeClass('modal-open');
        ReloadNumbers();
    });
    $('#tarefaModal').on('shown.bs.modal', function (e) {
        $('body').addClass('modal-open');
        $('#tarefaModal [name=nome]').focus();
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
    //var form = $('#eapModal [name]');
    var obj = Object();
    obj.projeto = {id: (parseInt($('#pro_id').val()) || 0)};
    var pai_id = parseInt($('#eapModal [name="pai.id"]').val()) || 0;
    if (pai_id > 0)
        obj.pai = {id: pai_id};
    obj.id = parseInt($('#eapModal [name="id"]').val()) || 0;
    obj.ordem = parseInt($('#eapModal [name="ordem"]').val().split('.').slice(-1)[0]) || 0;
    obj.nome = $('#eapModal [name="nome"]').val();
    obj.descricao = $('#eapModal [name="descricao"]').val();
    obj.inicio = $('#eapModal [name="inicio"]').val();
    obj.termino = $('#eapModal [name="termino"]').val();
    obj.valor = parseFloat($('#eapModal [name="valor"]').val()) || 0;
    if ($.trim(obj.nome) != '') {
        ShowLoader();
        $.ajax({
            type: 'POST',
            url: '/GPLearning/api/eap/salvar',
            data: JSON.stringify(obj),
            dataType: 'json',
            contentType: "application/json",
            success: function (responser) {
                HideLoader();
                if (responser && responser > 0) {
                    var eap = $('.eap [name=id][value="' + obj.id + '"]').parents('.eap:eq()');
                    if (obj.id === 0)
                    {
                        eap = $('.HtmlExample .eap_item').clone();
                        $('#eapModal [name=id]').val(responser);
                        $('[name="id"][value="' + pai_id + '"]').parents('.eap_item:eq(0)').find('.eap_pai:eq(0)').append(eap);
                    }
                    $('#eapModal [name]').each(function (index, item) {
                        var name = $(item).attr('name');
                        var value = $(item).val();
                        eap.find('[name="' + name + '"]').val(value);
                    });
                    eap.find('.eap_nome').html(responser + '- ' + $('#eapModal [name=nome]').val());
                    $('#eapModal').modal('hide');
                }
            }, error: function (error) {
                HideLoader();
            }
        });
    }
});

$(document).on('click', '#eapModal .deletaEAP', function () {
    var id = parseInt($('#eapModal').find('[name=id]').val()) || 0;
    var confirm = false;
    if (id > 0) {
        confirm = window.confirm("Tem certeza que deseja excluir?\n Todas os subpacotes serão excluídos também!");
    }
    if (confirm) {
        ShowLoader();
        $.ajax({
            type: 'POST',
            url: '/GPLearning/api/eap/excluir',
            data: {eap_id: id},
            success: function (responser) {
                HideLoader();
                if (responser) {
                    var item = $('.eap [name=id][value="' + id + '"]').parents('.eap_item:eq(0)');
                    item.remove();
                    ReloadNumbers();
                    $('#eapModal').modal('hide');
                }
            }, error: function (error) {
                HideLoader();
            }
        });
    }
});

$(document).on('click', '.eapAdd', function () {
    var eap = $(this).parents('.eap:eq(0)');
    var ordem = $(eap).find('[name=ordem]').val();
    var childrens = $(eap).next('.eap_pai').children('.eap_item').length;
    ordem = ordem + '.' + (childrens + 1);
    $('#eapModal [name]').val('');
    $('#eapModal [name="pai.id"]').val($(eap).find('[name=id]').val());
    $('#eapModal [name=ordem]').val(ordem);
    $('#eapModal .modal-title').html('EAP - ' + ordem);
    $('#eapModal .deletaEAP').hide();
    $('#eapModal').modal('show');
});

function CalculaLargura() {
    var pacotes = $('.eap_list .eap_column');
    pacotes.each(function (index, item) {
        var pais = $(item).find('.eap_pai:not(:empty)').length;
        $(item).children('.eap').css('marginRight', (pais * 25) + 'px');
    });
}

function ReloadNumbers() {
    LoadIndex($('.tarefa_pai:eq(0)'), '');
//    $('.eap_item.eap_column').removeClass('eap_column');
//    $('.eap_list .eap_pai:eq(1) > .eap_item').addClass('eap_column');
//    CalculaLargura();
}

function LoadIndex(pai, v_i) {
    $(pai).children('.tarefa_item').each(function (index, item) {
        var index_label = v_i + (index + 1);
        $(item).children('.tarefa').find('.indice').html(index_label);
//        $(item).children('.tarefa').find('[name=ordem]').val(index_label);
        LoadIndex($(item).children('.tarefa_pai'), index_label + '.');
    });
}

function loadTarefa() {
    var pro_id = parseInt($('#pro_id').val()) || 0;
    if (pro_id > 0) {
        ShowLoader();
        $.ajax({
            type: 'GET',
            url: '/GPLearning/api/tarefa/index/' + pro_id,
            success: function (responser) {
                HideLoader();
                $('.tarefas tbody').html('');
                if (responser) {
                    printRecursiveEAP(responser);
//                    ReloadNumbers();
                }
            },
            error: function (error) {
                HideLoader();
            }
        });
    }
}

function printRecursiveTarefa(tarefa) {
    if (tarefa) {
        var html = $('.HtmlExample .tarefa').clone();
        $('.tarefas tbody').append(html);

//        if (tarefa.pai) {
//            $('[name="id"][value="' + tarefa.pai.id + '"]').parents('.tarefa_item:eq(0)').find('.tarefa_pai:eq(0)').append(html);
//            html.find('[name="pai.id"]').val(tarefa.pai.id);
//        } else if (tarefa.eap) {
//            $('[name="eap.id"][value="' + tarefa.eap.id + '"]').parents('.tarefa_item:eq(0)').find('.tarefa_pai:eq(0)').append(html);
//            html.find('[name="eap.id"]').val(tarefa.eap.id);
//        } else {
//            html = $('.HtmlExample .tarefa_pai:eq(0)').clone();
//            $('.tarefa_list').append(html);
//        }
        html.find('[name="id"]').val(tarefa.id);
        html.find('[name="nome"]').val(tarefa.nome);
        html.find('[name="inicio"]').val(tarefa.inicio);
        html.find('[name="termino"]').val(tarefa.termino);
        html.find('[name="conclusao"]').val(tarefa.conclusao);
        html.find('[name="marco"]').val(tarefa.marco);

        html.find('.nome').html(tarefa.id + ' - ' + tarefa.nome);

        if (tarefa.tarefas) {
            $(tarefa.tarefas).each(function (index, item) {
                printRecursiveTarefa(item);
            });
        }
    }
}

function printRecursiveEAP(eap) {
    if (eap) {
        var html = $('.HtmlExample .tarefa').clone();
        $('.tarefas tbody').append(html);

        html.find('[name="eap.id"]').val(eap.id);

        html.find('.nome').html(eap.id + ' - ' + eap.nome);
        if (eap.tarefas) {
            $(eap.tarefas).each(function (index, item) {
                printRecursiveTarefa(item);
            });
        }
        if (eap.eaps) {
            $(eap.eaps).each(function (index, item) {
                printRecursiveEAP(item);
            });
        }
    }
}