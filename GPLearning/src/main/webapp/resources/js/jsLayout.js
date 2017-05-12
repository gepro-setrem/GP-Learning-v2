function ShowLoader() {
    try {
        $("#AdmLoader").show()
    } catch (n) {
    }
}
function HideLoader() {
    try {
        $("#AdmLoader").hide();
    } catch (n) {
    }
}


var Icons = {
    time: "fa fa-clock-o",
    date: "fa fa-calendar",
    up: "fa fa-chevron-up",
    down: "fa fa-chevron-down",
    previous: "fa fa-chevron-left",
    next: "fa fa-chevron-right",
    today: "fa fa-home",
    clear: "fa fa-trash",
    close: "fa fa-remove"
};

var Tooltips = {
    today: 'Hoje',
    clear: 'Limpar Campo',
    close: 'Fechar Aba',
    selectMonth: 'Selecionar Mês',
    prevMonth: 'Mês Anterior',
    nextMonth: 'Próximo Mês',
    selectYear: 'Selecionar Ano',
    prevYear: 'Ano Anterior',
    nextYear: 'Próximo Ano',
    selectDecade: 'Selecionar Década',
    prevDecade: 'Década Anterior',
    nextDecade: 'Próxima Década',
    prevCentury: 'Século Anterior',
    nextCentury: 'Próximo Século'
};