var map;
var markers = [[30.286, -97.739, 'stuff stuff'], [30.285, -97.73, 'trialhead2']];

function initialize() {
    var mapOptions = {
              center: new google.maps.LatLng(30.286, -97.739),
              zoom: 15
    };
    var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);

    // intialize the info window
    var contentString;
    var infowindow = new google.maps.InfoWindow({content: contentString});

    var hiddenMaxMarkers = document.getElementById("hiddenGameSize").value;

    for (var i = 0; i < hiddenMaxMarkers; i++) {
        // obtain the attribues of each marker

        //var contentString = "<html><body><div><p><h2>" + locationName + "</h2></p></div></body></html>";

        // make the markers
        var lat = document.getElementById("hiddenLat"+i).value;
        var lng = document.getElementById("hiddenLng"+i).value;
        var locationName = document.getElementById("hiddenLocationName"+i).value;
        var marker = new google.maps.Marker({
            position: new google.maps.LatLng(lat, lng),
            map: map,
            title: "Coordinates: " + lat + " , " + lng
        });

        // changes the info window to desired content
        google.maps.event.addListener(marker, 'click', (function(marker, i) {
            return function(){
                var contentString =
                "<b>Sport:</b> " + document.getElementById("hiddenSport"+i).value + 
                "<br><b>Location:</b> " + document.getElementById("hiddenLocationName"+i).value +
                "<br><b>Time:</b> " + document.getElementById("hiddenStartTime"+i).value + " - " + document.getElementById("hiddenEndTime"+i).value +
                "<br><b>Date:</b> " + 
                document.getElementById("hiddenMonth"+i).value + " " +
                document.getElementById("hiddenDay"+i).value + " , " +
                document.getElementById("hiddenYear"+i).value  +
                "<br><b>Players:</b> " + document.getElementById("hiddenPlayers"+i).value + "/ " + document.getElementById("hiddenMaxPlayers"+i).value;
                infowindow.setContent(contentString);
                infowindow.open(map,marker);
            }
        })(marker,i));
     }
}

function change(ref){
        if (ref.value === "Subscribe" ){
          ref.value = "Unsubscribe";
          return alert("You have been subscribed!");
        }
        else{
            ref.value = "Subscribe";
            return alert("You have been unsubscribed!");
        }
      }

google.maps.event.addDomListener(window, 'load', initialize);