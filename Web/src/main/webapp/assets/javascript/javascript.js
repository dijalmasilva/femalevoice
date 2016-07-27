/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    
    $('.modal-dark').click(function () {
        cancelar();
    });
});

function cadastrar() {
    mostrarAnimacoesModal();
    $('#modal-cadastro').fadeIn(1000);
}

function login() {
    mostrarAnimacoesModal();
    $('#modal-login').fadeIn(1000);
}

function novaDenuncia(){
    mostrarAnimacoesModal();
    $('#modal-denuncia').fadeIn(1000);
}

function mostrarAnimacoesModal(){
    $('.modal-event').removeClass('animated bounceOutUp');
    $('.modal-event').addClass('animated pulse');
}

function cancelar() {
    $('.modal-event').removeClass('animated tada');
    $('.modal-event').addClass('animated bounceOutUp');
    $('.modal-dijalma').delay(500);
    $('.modal-dijalma').fadeOut(200);
}