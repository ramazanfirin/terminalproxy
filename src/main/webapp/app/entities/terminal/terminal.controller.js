(function() {
    'use strict';

    angular
        .module('terminalproxyApp')
        .controller('TerminalController', TerminalController);

    TerminalController.$inject = ['Terminal'];

    function TerminalController(Terminal) {

        var vm = this;

        vm.terminals = [];

        loadAll();

        function loadAll() {
            Terminal.query(function(result) {
                vm.terminals = result;
                vm.searchQuery = null;
            });
        }
    }
})();
