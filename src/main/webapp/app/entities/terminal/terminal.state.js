(function() {
    'use strict';

    angular
        .module('terminalproxyApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('terminal', {
            parent: 'entity',
            url: '/terminal',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'terminalproxyApp.terminal.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/terminal/terminals.html',
                    controller: 'TerminalController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('terminal');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('terminal-detail', {
            parent: 'terminal',
            url: '/terminal/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'terminalproxyApp.terminal.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/terminal/terminal-detail.html',
                    controller: 'TerminalDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('terminal');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Terminal', function($stateParams, Terminal) {
                    return Terminal.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'terminal',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('terminal-detail.edit', {
            parent: 'terminal-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/terminal/terminal-dialog.html',
                    controller: 'TerminalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Terminal', function(Terminal) {
                            return Terminal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('terminal.new', {
            parent: 'terminal',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/terminal/terminal-dialog.html',
                    controller: 'TerminalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('terminal', null, { reload: 'terminal' });
                }, function() {
                    $state.go('terminal');
                });
            }]
        })
        .state('terminal.edit', {
            parent: 'terminal',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/terminal/terminal-dialog.html',
                    controller: 'TerminalDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Terminal', function(Terminal) {
                            return Terminal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('terminal', null, { reload: 'terminal' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('terminal.delete', {
            parent: 'terminal',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/terminal/terminal-delete-dialog.html',
                    controller: 'TerminalDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Terminal', function(Terminal) {
                            return Terminal.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('terminal', null, { reload: 'terminal' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
