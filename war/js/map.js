var global_markers = [];
var map;
var markers = [[30.286 -97.739, 'stuff stuff'], [-14.235004, -51.92528, 'trialhead1'], [-38.416097, -63.616672, 'trialhead2']];

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
	  '<p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large ' +
	  'sandstone rock formation in the southern part of the '+
	  'Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) '+
	  'south west of the nearest large town, Alice Springs; 450&#160;km '+
	  '(280&#160;mi) by road. Kata Tjuta and Uluru are the two major '+
	  'features of the Uluru - Kata Tjuta National Park. Uluru is '+
	  'sacred to the Pitjantjatjara and Yankunytjatjara, the '+
	  'Aboriginal people of the area. It has many springs, waterholes, '+
	  'rock caves and ancient paintings. Uluru is listed as a World '+
	  'Heritage Site.</p>'+
	  '<p>Attribution: Uluru, <a href="http://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'+
	  'http://en.wikipedia.org/w/index.php?title=Uluru</a> '+
	  '(last visited June 22, 2009).</p>'+
	  '</div>'+
	  '</div>';

	var infowindow = new google.maps.InfoWindow({
	  content: contentString
	});

	var marker = new google.maps.Marker({
	  position: new google.maps.LatLng(30.286, -97.739),
	  map: map,
	  title: 'Uluru (Ayers Rock)'
	});

	google.maps.event.addListener(marker, 'click', function() {infowindow.open(map,marker);});



	addMarker();
}

function addMarker() {
	// doesnt seem to work
    for (var i = 0; i < markers.length; i++) {
        // obtain the attribues of each marker
        var lat = parseFloat(markers[i][0]);
        var lng = parseFloat(markers[i][1]);
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
            infowindow.setContent(this['infowindow']);
            infowindow.open(map, this);
        });
    }
}

google.maps.event.addDomListener(window, 'load', initialize);