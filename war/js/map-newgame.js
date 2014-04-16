var map;
var marked = false;

function initialize() {
        var mapOptions = {
          center: new google.maps.LatLng(30.286, -97.739),
          zoom: 15
        };
        var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
        
        var contentString;
        var infowindow = new google.maps.InfoWindow({content: contentString});         
        
        // adding new markers
        function placeMarker(location) {
            var marker = new google.maps.Marker({
                position: location, 
                map: map,
                draggable:true
            });

            $("#latitude").val(marker.getPosition().lat());
            $("#longitude").val(marker.getPosition().lng());

            // makes the info window pop up on new markers
            google.maps.event.addListener(marker, 'click', (function(marker) {
                return function(){
                    var marked=true;
                    infowindow.setContent("It's a new Marker!");
                    infowindow.open(map,marker);
                    $("#latitude").val(marker.getPosition().lat());
                    $("#longitude").val(marker.getPosition().lng());
                }
            })(marker));

            // changes the coordinates as we drag the markers   
            google.maps.event.addListener(marker, 'position_changed', (function(marker) {
                return function(){
                    $("#latitude").val(marker.getPosition().lat());
                    $("#longitude").val(marker.getPosition().lng());
                    var contentString = "Coordinates: " + marker.getPosition(); 
                    infowindow.setContent(contentString);
                    infowindow.open(map,marker);
                    marker.title= contentString;
                }
            })(marker));
        }
         
        // calls the place marker function when the user clicks
        google.maps.event.addListener(map, 'click', function(event) {
            if (marked==false){
                placeMarker(event.latLng);
                marked=true;
            }
        });
}

google.maps.event.addDomListener(window, 'load', initialize);