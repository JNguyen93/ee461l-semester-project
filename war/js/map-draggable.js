var global_markers = [];
var map;
var markers = [[30.286, -97.739, 'stuff stuff'], [30.28, -97.7, 'trialhead1'], [30.285, -97.73, 'trialhead2']];
var marked = false;
var latitude;
var longitude;

function initialize() {
        var mapOptions = {
          center: new google.maps.LatLng(30.286, -97.739),
          zoom: 15
        };
        var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
        
        var contentString = '<div id="content">'+
          '<div id="siteNotice">'+
          '</div>'+
          '<h1 id="firstHeading" class="firstHeading">UT Austin</h1>'+
          '<div id="bodyContent">'+
          '<p><b>This is UT</b>, </p>'+
          '</div>'+
          '</div>';
// intialize the info window
        var infowindow = new google.maps.InfoWindow({
          content: contentString
        });

         for (var i = 0; i < markers.length; i++) {
                // obtain the attribues of each marker
                var lat = markers[i][0];
                var lng = markers[i][1];
                var trailhead_name = markers[i][2];
               
                var myLatlng = new google.maps.LatLng(lat, lng);

                var contentString = "<html><body><div><p><h2>" + trailhead_name + "</h2></p></div></body></html>";
                // make the markers
                var marker = new google.maps.Marker({
                    position: myLatlng,
                    map: map,
                    title: "Coordinates: " + lat + " , " + lng + " | Trailhead name: " + trailhead_name,
                   // draggable:true
                });
               // global_markers[i] = marker;
                // changes the info window to desired content
                google.maps.event.addListener(marker, 'click', (function(marker, i) {
                        return function(){
                                infowindow.setContent(markers[i][2]);
                                infowindow.open(map,marker);
                        }
                })(marker,i));
                
                
             
            }
         
                // adding new markers
         function placeMarker(location) {
                    var marker = new google.maps.Marker({
                        position: location, 
                        map: map,
                        draggable:true
                    });
                    // makes the info window pop up on new markers
                    google.maps.event.addListener(marker, 'click', (function(marker) {
                                return function(){
                                        var marked=true;
                                        infowindow.setContent("It's a new Marker!");
                                        infowindow.open(map,marker);
                                        latitude = marker.getPosition().lat();
                                        longitude = marker.getPosition().lng();
                                        $("#latitude").val(marker.getPosition().lat());
                                        $("#longitude").val(marker.getPosition().lng());
                                }
                    })(marker));
                    // changes the coordinates as we drag the markers   
                        google.maps.event.addListener(marker, 'position_changed', (function(marker) {
                                return function(){
                                        latitude = marker.getPosition().lat();
                                        longitude = marker.getPosition().lng();
                                        $("#latitude").val(marker.getPosition().lat());
                                        $("#longitude").val(marker.getPosition().lng());
                                        var s = "Coordinates: " + marker.getPosition(); 
                                        infowindow.setContent(s);
                                        infowindow.open(map,marker);
                                        marker.title= s;
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
         
        

                

//      addMarker();
}

function addMarker() {
        // doesnt seem to work
    for (var i = 0; i < markers.length; i++) {
        // obtain the attribues of each marker
        var lat = markers[i][0];
        var lng = markers[i][1];
        var trailhead_name = markers[i][2];

        var myLatlng = new google.maps.LatLng(lat, lng);

        var contentString = "<html><body><div><p><h2>" + trailhead_name + "</h2></p></div></body></html>";

        var marker = new google.maps.Marker({
            position: myLatlng,
            map: map,
            title: "Coordinates: " + lat + " , " + lng + " | Trailhead name: " + trailhead_name
        });

        marker['infowindow'] = contentString;
     
        global_markers[i] = marker;

        google.maps.event.addListener(global_markers[i], 'click', function() {
           
            infowindow.open(map, global_markers[i]);
        });
    }
}

google.maps.event.addDomListener(window, 'load', initialize);