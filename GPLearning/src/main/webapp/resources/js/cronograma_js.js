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
$(document).on('click', '.marco [type=checkbox]', function () {
    var tarefa = $(this).parents('.tarefa:eq(0)');
    var id = parseInt(tarefa.find('[name=id]').val()) || 0;
    var isCheck = $(this).prop('checked');
    tarefa.find('[name=marco]').val(isCheck);
    if (id > 0) {
        $.ajax({
            type: 'POST',
            url: '/GPLearning/api/tarefa/marcar',
            data: {id: id, marco: isCheck},
            success: function (responser) {
                HideLoader();
                if (responser) {
                }
            }, error: function (error) {
                HideLoader();
            }
        });
    }
});
$(document).on('click', '.tarefaEdit', function () {
    var tarefa = $(this).parents('.tarefa:eq(0)');
    //var ordem = eap.find('[name=ordem]').val();
    tarefa.find('[name]').each(function (index, item) {
        var name = $(item).attr('name');
        var value = $(item).val();
        $('#tarefaModal [name="' + name + '"]').val(value);
    });
    //$('#tarefaModal .modal-title').html('EAP - ' + ordem);
    $('#tarefaModal .deletaEAP').show();
    $('#tarefaModal .recursos > tbody').html('');
    tarefa.find('.lsRecursos [name=recurso]').each(function (index, item) {
        var html = $('.HtmlExample .recurso').clone();
        html.find('[name=recurso]').val($(item).val());
        $('#tarefaModal .recursos > tbody').append(html);
    });
    $('#tarefaModal').modal('show');
});
$(document).on('click', '#tarefaModal .addRecurso', function () {
    var html = $('.HtmlExample .recurso').clone();
    $('#tarefaModal .recursos > tbody').append(html);
});
$(document).on('click', '#tarefaModal .deletaRecurso', function () {
    $(this).parents('.recurso:eq(0)').remove();
});
$(document).on('click', '#tarefaModal .salvaTarefa', function () {
//var form = $('#eapModal [name]');
    var obj = Object();
    //obj.projeto = {id: (parseInt($('#pro_id').val()) || 0)};
    var pai_id = parseInt($('#tarefaModal [name="pai.id"]').val()) || 0;
    if (pai_id > 0)
        obj.pai = {id: pai_id};
    var eap_id = parseInt($('#tarefaModal [name="eap.id"]').val()) || 0;
    if (eap_id > 0)
        obj.eap = {id: eap_id};
    obj.id = parseInt($('#tarefaModal [name="id"]').val()) || 0;
    obj.nome = $('#tarefaModal [name="nome"]').val();
    obj.marco = $('#tarefaModal [name="marco"]').val();
    obj.conclusao = $('#tarefaModal [name="conclusao"]').val();
    obj.inicio = $('#tarefaModal [name="inicio"]').val();
    obj.termino = $('#tarefaModal [name="termino"]').val();
    if ($.trim(obj.conclusao) != '')
        obj.conclusao += 'T00:00:00.000-03:00';
    if ($.trim(obj.inicio) != '')
        obj.inicio += 'T00:00:00.000-03:00';
    if ($.trim(obj.termino) != '')
        obj.termino += 'T00:00:00.000-03:00';
    obj.recursos = [];
    $('#tarefaModal [name="recurso"]').each(function (index, item) {
        var recurso = $.trim($(item).val());
        if (recurso != '') {
            obj.recursos.push({nome: recurso});
        }
    });
    var isValid = $.trim(obj.nome) != '';
    if (!isValid)
        alert("O nome é obrigatório!")
    if (isValid) {
        isValid = obj.inicio <= obj.termino;
        if (!isValid)
            alert("A data de início não pode ser maior que a data de término!")
    }
    if (isValid) {
        ShowLoader();
        $.ajax({
            type: 'POST',
            url: '/GPLearning/api/tarefa/salvar',
            data: JSON.stringify(obj),
            dataType: 'json',
            contentType: "application/json",
            success: function (responser) {
                HideLoader();
                if (responser && responser > 0) {
                    var tarefa = $('.tarefa [name=id][value="' + obj.id + '"]').parents('.tarefa:eq(0)');
                    if (obj.id === 0)
                    {
                        tarefa = $('.HtmlExample .tarefa').clone();
                        $('#tarefaModal [name=id]').val(responser);
                        if (pai_id > 0) {
                            tarefa.insertAfter($('[name="id"][value="' + pai_id + '"]').parents('.tarefa:eq(0)'));
                        } else if (eap_id) {
                            tarefa.insertAfter($('[name="eap.id"][value="' + eap_id + '"]').parents('.tarefa:eq(0)'));
                        }
                    }
                    $('#tarefaModal [name]').each(function (index, item) {
                        var name = $(item).attr('name');
                        var value = $(item).val();
                        tarefa.find('[name="' + name + '"]').val(value);
                    });
                    tarefa.find('.lsRecursos').html('');
                    var recursos = '';
                    $(obj.recursos).each(function (index, item) {
                        recursos += item.nome + ', ';
                        tarefa.find('.lsRecursos').append('<input type="hidden" name="recurso" value="' + item.nome + '"/>');
                    });
                    tarefa.find('.recursos').html(recursos.slice(0, -2));
                    tarefa.find('.nome').html(obj.nome);
                    tarefa.find('.inicio').html(ToDate(obj.inicio));
                    tarefa.find('.termino').html(ToDate(obj.termino));
                    ReloadNumbers();
                    $('#tarefaModal').modal('hide');
                }
            }, error: function (error) {
                HideLoader();
            }
        });
    }
});
$(document).on('click', '#tarefaModal .deletaTarefa', function () {
    var id = parseInt($('#tarefaModal').find('[name=id]').val()) || 0;
    var confirm = false;
    if (id > 0) {
        confirm = window.confirm("Tem certeza que deseja excluir?\n Todas as subtarefas serão excluídos também!");
    }
    if (confirm) {
        ShowLoader();
        $.ajax({
            type: 'POST',
            url: '/GPLearning/api/tarefa/excluir',
            data: {id: id},
            success: function (responser) {
                HideLoader();
                if (responser) {
                    DeleteRecursive(id);
                    ReloadNumbers();
                    $('#tarefaModal').modal('hide');
                }
            }, error: function (error) {
                HideLoader();
            }
        });
    }
});
function DeleteRecursive(id) {
    if (id > 0) {
        $('.tarefa [name="pai.id"][value="' + id + '"]').parents('.tarefa').each(function (index, item) {
            var id2 = parseInt($(item).find('[name=id]').val()) || 0;
            DeleteRecursive(id2);
        });
        $('.tarefa [name=id][value="' + id + '"]').parents('.tarefa:eq(0)').remove();
    }
}

$(document).on('click', '.tarefaAdd', function () {
    var tarefa = $(this).parents('.tarefa:eq(0)');
    $('#tarefaModal .recursos > tbody').html('');
    $('#tarefaModal [name]').val('');
    var id = parseInt($(tarefa).find('[name="id"]').val()) || 0;
    //var idPai = parseInt($(tarefa).find('[name="pai.id]"').val()) || 0;
    var idEap = parseInt($(tarefa).find('[name="eap.id"]').val()) || 0;
    if (id > 0)
        $('#tarefaModal [name="pai.id"]').val(id);
    else if (idEap > 0)
        $('#tarefaModal [name="eap.id"]').val(idEap);
    //$('#tarefaModal [name=ordem]').val(ordem);
    //$('#eapModal .modal-title').html('EAP - ' + ordem);
    $('#tarefaModal .deletaTarefa').hide();
    if (id > 0 || idEap > 0)
        $('#tarefaModal').modal('show');
});
function CalculaLargura() {
    var pacotes = $('.eap_list .eap_column');
    pacotes.each(function (index, item) {
        var pais = $(item).find('.eap_pai:not(:empty)').length;
        $(item).children('.eap').css('marginRight', (pais * 25) + 'px');
    });
}

function ReloadNumbers() {
    $('.tarefas .tarefa [name=id]:not([value])~[name="eap.pai.id"]:not([value])').parents('.tarefa').each(function (index, item) {
        var idPai = parseInt($(item).find('[name="id"]').val()) || 0;
        var idEap = parseInt($(item).find('[name="eap.id"]').val()) || 0;
        var index_label = index + 1;
        $('.tarefas .tarefa [name=id]:not([value])~[name="eap.id"][value="' + idEap + '"]').parents('.tarefa').find('.indice').html(index_label);
        LoadIndex(idPai, idEap, index_label + '.');
    });
    //LoadIndex($('.tarefa_pai:eq(0)'), '');
//    $('.eap_item.eap_column').removeClass('eap_column');
//    $('.eap_list .eap_pai:eq(1) > .eap_item').addClass('eap_column');
//    CalculaLargura();
}

function LoadIndex(idPai, idEap, v_i) {
    if (idPai > 0) {
        $('.tarefas .tarefa [name="pai.id"][value="' + idPai + '"]').each(function (index, item) {
            var tarefa = $(item).parents('.tarefa:eq(0)');
            var idPai = parseInt($(tarefa).find('[name="id"]').val()) || 0;
            var idEap = parseInt($(tarefa).find('[name="eap.id"]').val()) || 0;
            var index_label = v_i + (index + 1);
            $('.tarefas .tarefa [name="id"][value="' + idPai + '"]').parents('.tarefa').find('.indice').html(index_label);
            LoadIndex(idPai, idEap, index_label + '.');
        });
    } else if (idEap > 0) {
        $('.tarefas .tarefa [name=id]:not([value])~[name="eap.pai.id"][value="' + idEap + '"]').each(function (index, item) {
            var tarefa = $(item).parents('.tarefa:eq(0)');
            var idPai = parseInt($(tarefa).find('[name="id"]').val()) || 0;
            var idEap = parseInt($(tarefa).find('[name="eap.id"]').val()) || 0;
            var index_label = v_i + (index + 1);
            $('.tarefas .tarefa [name=id]:not([value])~[name="eap.id"][value="' + idEap + '"]').parents('.tarefa').find('.indice').html(index_label);
            LoadIndex(idPai, idEap, index_label + '.');
        });
        $('.tarefas .tarefa [name=id][value]~[name="eap.id"][value="' + idEap + '"]').each(function (index, item) {
            var tarefa = $(item).parents('.tarefa:eq(0)');
            var idPai = parseInt($(tarefa).find('[name="id"]').val()) || 0;
            var idEap = parseInt($(tarefa).find('[name="eap.id"]').val()) || 0;
            var index_label = v_i + (index + 1);
            $('.tarefas .tarefa [name="id"][value="' + idPai + '"]').parents('.tarefa').find('.indice').html(index_label);
            LoadIndex(idPai, idEap, index_label + '.');
        });
    }
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
                    ReloadNumbers();
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
        if (tarefa.pai)
            html.find('[name="pai.id"]').val(tarefa.pai.id);
        if (tarefa.eap)
            html.find('[name="eap.id"]').val(tarefa.eap.id);
        html.find('[name="id"]').val(tarefa.id);
        html.find('[name="nome"]').val(tarefa.nome);
        html.find('[name="inicio"]').val(tarefa.inicio);
        html.find('[name="termino"]').val(tarefa.termino);
        html.find('[name="conclusao"]').val(tarefa.conclusao);
        html.find('[name="marco"]').val(tarefa.marco);
        html.find('.marco [type=checkbox]').prop('checked', tarefa.marco);
        html.find('.inicio').html(ToDate(tarefa.inicio));
        html.find('.termino').html(ToDate(tarefa.termino));
        var recursos = '';
        if (tarefa.recursos) {
            $(tarefa.recursos).each(function (index, item) {
                recursos += item.nome + ', ';
                html.find('.lsRecursos').append('<input type="hidden" name="recurso" value="' + item.nome + '"/>');
            });
        }
        html.find('.recursos').html(recursos.slice(0, -2));
        html.find('.nome').html(tarefa.nome);
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
        html.find('.marco').html('');
        html.addClass('eap');
        html.find('.inicio').html(ToDate(eap.inicio));
        html.find('.termino').html(ToDate(eap.termino));
        html.find('.tarefaEdit').remove();
        html.find('[name="eap.id"]').val(eap.id);
        if (eap.pai)
            html.find('[name="eap.pai.id"]').val(eap.pai.id);
        html.find('.nome').html(eap.nome);
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

function ToDate(date) {
    date = date || '';
    if (date.indexOf('T') < 0)
        date += 'T00:00';
    var d = new Date(date);
    if (d != 'Invalid Date') {
        var dia = d.getDate();
        if (dia < 10)
            dia = '0' + dia;
        var mes = d.getMonth() + 1;
        if (mes < 10)
            mes = '0' + mes;
        var ano = d.getFullYear();
        return dia + '/' + mes + '/' + ano;
    }
    return "";
}