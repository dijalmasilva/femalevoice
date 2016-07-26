var geocoder;
var map;
var marker;

function initialize() {
    var latlng = new google.maps.LatLng(-18.8800397, -47.05878999999999);
    var options = {
        zoom: 5,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(document.getElementById("map"), options);

    geocoder = new google.maps.Geocoder();

    marker = new google.maps.Marker({
        map: map,
        draggable: true,
    });

    marker.setPosition(latlng);
}

// verifica se o navegador tem suporte a geolocalização
if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(function (position) { // callback de sucesso
        // ajusta a posição do marker para a localização do usuário
        marker.setPosition(new google.maps.LatLng(position.coords.latitude, position.coords.longitude));
        map.setCenter(marker.getPosition());
        console.log(marker.getPosition().lat());
        console.log(marker.getPosition().lng());
        map.setZoom(16);
        geocoder.geocode({'latLng': marker.getPosition()}, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    $('.address').val(results[0].formatted_address);
                    $('#txtLatitude').val(marker.getPosition().lat());
                    $('#txtLongitude').val(marker.getPosition().lng());
                }
            }
        });
    },
            function (error) { // callback de erro
                alert('Erro ao obter localização!');
                console.log('Erro ao obter localização.', error);
            });
} else {
    console.log('Navegador não suporta Geolocalização!');
}

$(document).ready(function () {

    initialize();

    google.maps.event.addListener(marker, 'drag', function () {
        geocoder.geocode({'latLng': marker.getPosition()}, function (results, status) {
            if (status === google.maps.GeocoderStatus.OK) {
                if (results[0]) {
                    $('.address').val(results[0].formatted_address);
                    $('#txtLatitude').val(marker.getPosition().lat());
                    $('#txtLongitude').val(marker.getPosition().lng());
                }
            }
        });
    });

    $(".address").autocomplete({
        source: function (request, response) {
            $('option').remove();
            geocoder.geocode({'address': request.term + ', Brasil', 'region': 'BR'}, function (results, status) {
                response($.map(results, function (item) {
                    carregaAutoComplete(item);
//                    void {
//                        label: item.formatted_address,
//                        value: item.formatted_address,
//                        latitude: item.geometry.location.lat(),
//                        longitude: item.geometry.location.lng()
//                    };
                }));
            });
        },
        select: function (event, ui) {
            $("#txtLatitude").val(ui.item.latitude);
            $("#txtLongitude").val(ui.item.longitude);
            var location = new google.maps.LatLng(ui.item.latitude, ui.item.longitude);
            marker.setPosition(location);
            map.setCenter(location);
            map.setZoom(16);
        }
    });

    $('.setPlaces').trigger('click');
});

function carregarNoMapa() {
    var endereco = $('.address').val();
    carregaLocalizacaoNoEndereco(endereco);
}


function carregaLocalizacaoNoEndereco(endereco) {
    geocoder.geocode({'address': endereco + ', Brasil', 'region': 'BR'}, function (results, status) {
        if (status === google.maps.GeocoderStatus.OK) {
            if (results[0]) {
                var latitude = results[0].geometry.location.lat();
                var longitude = results[0].geometry.location.lng();

                $('.address').val(results[0].formatted_address);
                $('#txtLatitude').val(latitude);
                $('#txtLongitude').val(longitude);

                var location = new google.maps.LatLng(latitude, longitude);
                marker.setPosition(location);
                map.setCenter(location);
                map.setZoom(16);
            }
        }
    });
}

function carregaAutoComplete(item) {
    console.log(item.formatted_address);
    $('#enderecos').append("<option>" + item.formatted_address + "</optioin>");
}


var imagens = {
    assedio: "/assets/icons/assedio.png",
    estupro: "/assets/icons/estupro.png",
    agressao: "/assets/icons/agressao.png"
};

var marcadores = [];
var criaMarcador = function (marcador, mapa) {
    var posicao = new google.maps.LatLng(marcador.latitude, marcador.longitude);
    var opcoes = {
        position: posicao
        , title: marcador.titulo
        , animation: google.maps.Animation.DROP
        , icon: {
            url: marcador.imagem || 'http://i.imgur.com/eNAvIvr.png'
            , scaledSize: new google.maps.Size(50, 50)
        }
        , map: mapa
    };
    var novoMarcador = new google.maps.Marker(opcoes);
    marcadores.push(novoMarcador);
//    map.setCenter(novoMarcador.position)
};

function adiciona(lat, long, descricao, tipo, idLugar) {
    console.log("Imprimindo " + idLugar + ":" + lat, long, descricao, tipo, idLugar);
    var marcador = {
        latitude: lat
        , longitude: long
        , id: idLugar
        , titulo: descricao
        , imagem: verificaTipoImagem(tipo)
    };
    criaMarcador(marcador, map);
}

function verificaTipoImagem(tipo) {
    var teste = tipo.toLowerCase();
    console.log(teste.localeCompare('agressao'));
    if (teste.localeCompare('agressao')){
        return imagens.agressao;
    } else if (teste.localeCompare("assedio")) {
        return imagens.assedio;
    } else {
        return imagens.estupro;
    }
}