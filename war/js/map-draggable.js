var global_markers = [];
var map;
var markers = [[30.286, -97.739, 'stuff stuff'], [30.28, -97.7, 'trialhead1'], [30.285, -97.73, 'trialhead2']];

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

	        var marker = new google.maps.Marker({
	            position: myLatlng,
	            map: map,
	            draggable:true,
	            title: "Coordinates: " + lat + " , " + lng + " | Trailhead name: " + trailhead_name
	        });
	       // global_markers[i] = marker;

	        google.maps.event.addListener(marker, 'click', (function(marker, i) {
	        	return function(){
	        		infowindow.setContent(markers[i][2]);
	        		infowindow.open(map,marker);
	        	}
	        })(marker,i));
	       
    		google.maps.event.addListener(marker, 'position_changed', (function(marker, i) {
	        	return function(){
	        		var s = "Coordinates: " + marker.getPosition(); 
	         		marker.title= s;
	        	}
	        })(marker,i));
	    }
	 function placeMarker(location) {
		    var marker = new google.maps.Marker({
		        position: location, 
		        map: map,
		        draggable:true
		    });
		    google.maps.event.addListener(marker, 'click', (function(marker) {
		      	return function(){
		      		infowindow.setContent("It's a new Marker!");
		      		infowindow.open(map,marker);
		      	}
		      })(marker));
		}
	 google.maps.event.addListener(map, 'click', function(event) {
		   placeMarker(event.latLng);
		});
	 
	

		

//	addMarker();
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