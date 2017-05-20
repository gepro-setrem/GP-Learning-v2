var token = 0;
var pro_id = 0;
$(function () {
    token = $('#token').val();
    pro_id = parseInt($('#pro_id').val()) || 0;
    loadEAP();
    ApplyDateTime('#eapModal [name=inicio]');
    ApplyDateTime('#eapModal [name=termino]');

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
//    var inicio = moment(eap.find('[name=inicio]').val(), "YYYY-MM-DD").format('DD/MM/YYYY');
//    if (inicio == 'Invalid date')
//        inicio = '';
//    $('#eapModal [name=inicio]').val(inicio);
//
//    var inicio = moment(eap.find('[name=termino]').val(), "YYYY-MM-DD").format('DD/MM/YYYY');
//    if (inicio == 'Invalid date')
//        inicio = '';
//    $('#eapModal [name=termino]').val(inicio);

    $('#eapModal').modal('show');
});

$(document).on('click', '#eapModal .salvaEAP', function () {
    //var form = $('#eapModal [name]');
    var obj = Object();
    obj.projeto = {id: pro_id};
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
    if ($.trim(obj.inicio) != '')
        obj.inicio = moment(obj.inicio, 'DD/MM/YYYY HH:mm:ss').format('YYYY-MM-DDTHH:mm:ssZZ');
    //obj.inicio += 'T00:00:00.000-03:00';
    if ($.trim(obj.termino) != '')
        obj.termino = moment(obj.termino, 'DD/MM/YYYY HH:mm:ss').format('YYYY-MM-DDTHH:mm:ssZZ');
    //  obj.termino += 'T00:00:00.000-03:00';
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
            url: '/GPLearning/api/eap/salvar',
            data: JSON.stringify(obj),
            dataType: 'json',
            contentType: "application/json",
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', token);
            },
            success: function (responser) {
                HideLoader();
                if (responser && responser > 0) {
                    var eap = $('.eap [name=id][value="' + obj.id + '"]').parents('.eap:eq(0)');
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
                    eap.find('.eap_nome').html($('#eapModal [name=nome]').val());
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
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', token);
            },
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

function loadEAP() {
    if (pro_id > 0) {
        ShowLoader();
        $.ajax({
            type: 'GET',
            url: '/GPLearning/api/eap/index/' + pro_id,
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Authorization', token);
            },
            success: function (responser) {
                HideLoader();
                $('.eap_list').html('');
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

function printRecursiveEAP(eap) {
    if (eap) {
        var html = $('.HtmlExample .eap_item:eq(0)').clone();
        if (eap.pai) {
            $('[name="id"][value="' + eap.pai.id + '"]').parents('.eap_item:eq(0)').find('.eap_pai:eq(0)').append(html);
            html.find('[name="pai.id"]').val(eap.pai.id);
        } else {
            html = $('.HtmlExample .eap_pai:eq(0)').clone();
            $('.eap_list').append(html);
        }
        html.find('[name="id"]').val(eap.id);
        html.find('[name="ordem"]').val(eap.ordem);
        html.find('[name="nome"]').val(eap.nome);
        html.find('[name="descricao"]').val(eap.descricao);
        if (eap.inicio) {
            eap.inicio = moment(eap.inicio, 'YYYY-MM-DDTHH:mm').format('DD/MM/YYYY HH:mm');
        }
        html.find('[name="inicio"]').val(eap.inicio);

        if (eap.termino) {
            eap.termino = moment(eap.termino, 'YYYY-MM-DDTHH:mm').format('DD/MM/YYYY HH:mm');
        }
        html.find('[name="termino"]').val(eap.termino);

        html.find('[name="valor"]').val(eap.valor);
        html.find('.eap_nome').html(eap.nome);

        if (eap.eaps) {
            $(eap.eaps).each(function (index, item) {
                printRecursiveEAP(item);
            });
        }
    }
}
//function number() {
//    return Math.floor((1 + Math.random()) * 0x10000);
//}

function ApplyDateTime(selectors) {//$(".Date")
    //$(selectors).mask("99/99/9999");//, { placeholder: "__/__/____" });
    $(selectors).datetimepicker({
        icons: Icons,
        tooltips: Tooltips,
        format: 'DD/MM/YYYY HH:mm',
        useCurrent: false,
        locale: 'pt-br', //moment.locale('pt-br'),
        toolbarPlacement: 'bottom',
        showTodayButton: true,
        showClear: true,
    }).on("dp.change", function (e) {});
}