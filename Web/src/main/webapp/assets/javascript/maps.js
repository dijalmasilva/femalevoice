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
        map.setZoom(16);
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
                    $('#address').val(results[0].formatted_address);
                    $('#txtLatitude').val(marker.getPosition().lat());
                    $('#txtLongitude').val(marker.getPosition().lng());
                }
            }
        });
    });

    $("#address").autocomplete({
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
});

function carregarNoMapa() {
    var endereco = $('#address').val();
    console.log(endereco);
    geocoder.geocode({'address': endereco + ', Brasil', 'region': 'BR'}, function (results, status) {
        if (status === google.maps.GeocoderStatus.OK) {
            if (results[0]) {
                var latitude = results[0].geometry.location.lat();
                var longitude = results[0].geometry.location.lng();

                $('#address').val(results[0].formatted_address);
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
;

function carregaAutoComplete(item) {
    console.log(item.formatted_address);
    $('#enderecos').append("<option>" + item.formatted_address + "</optioin>");
}