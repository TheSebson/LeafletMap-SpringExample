GET: $(document).ready(
		function() {
			var mymap = L.map('mapid').setView([51.505, 18], 13);
            ajaxGet();
                        	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
                        		maxZoom: 20,
                        		attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, ' +
                        			'<a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
                        			'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
                        		id: 'mapbox.streets'
                        	}).addTo(mymap);

            var popup = L.popup();
            function onMapClick(e) {
                             popup
                            .setLatLng(e.latlng)
                            .setContent( e.latlng.toString())
                            .openOn(mymap);
                            var lat = e.latlng.toString().split(',')[0];
                            var long = e.latlng.toString().split(',')[1];
            }

             function onMapClickDelete(e) {
                    var popup = e.target.getPopup();
                    var id = popup.getContent().split('.')[0];
                                          if (confirm("Czy chcesz usunąć ten punkt?")) {
                                            ajaxDelete(id);
                                          } else {

                                          }
                        }
             function onMapClickAdd(e) {
               var txt;
               var mark = prompt("Dodaj w tym miejscu znacznik:", "");
               var lat = e.latlng.toString().split(',')[0].replace("LatLng(", "");
               var long = e.latlng.toString().split(',')[1].replace(")","").trim();
               if (mark != null || mark != "") {
                 ajaxPost(lat, long, mark);
               }
             }

            mymap.on('click', onMapClickAdd);

			function ajaxGet() {
				$.ajax({
					type : "GET",
					url : "map/getMarkers",
					success : function(result) {
						if (result.status == "success") {
							var custList = "";
							$.each(result.data,
									function(i, aggregate) {
									if(i < 7){
                                        var icon = new L.Icon({
                                                        iconUrl: ('img/'+ i +'.png').trim(),
                                                        shadowUrl: 'img/marker-shadow.png',
                                                        iconSize: [25, 41],
                                                        iconAnchor: [12, 41],
                                                        popupAnchor: [1, -34],
                                                        shadowSize: [41, 41]
                                                    });
                                                }
									for(i = 0; i < aggregate.markers.length; i++){
									    L.marker([ aggregate.markers[i].latitude, aggregate.markers[i].longtitude],{icon: icon})
									    .addTo(mymap)
									    .bindPopup(aggregate.markers[i].id + "." + aggregate.markers[i].name)
									    .openPopup().on('dblclick', onMapClickDelete );
									}
									});
							console.log("Success: ", result);
						} else {
							console.log("Fail: ", result);
						}
					},
					error : function(e) {
						console.log("ERROR: ", e);
					}
				});
			}

			function ajaxPost(lat, long, name) {

            				var formData = {
            				    name : name,
            					latitude : lat,
            					longtitude : long
            				}
            				var icon = new L.Icon({
                                iconUrl: ('img/7.png').trim(),
                                shadowUrl: 'img/marker-shadow.png',
                                iconSize: [25, 41],
                                iconAnchor: [12, 41],
                                popupAnchor: [1, -34],
                                shadowSize: [41, 41]
                            });

            				$.ajax({
            					type : "POST",
            					contentType : "application/json",
            					url : "map/saveMarker",
            					data : JSON.stringify(formData),
            					dataType : 'json',
            					success : function(result) {
            						if (result.status == "success") {
                                        L.marker([ lat,long],{icon: icon})
                                        .addTo(mymap)
                                        .bindPopup(name)
                                        .openPopup()
                                        .on('dblclick', onMapClickDelete );

            						}
            						console.log(result);
            					},
            					error : function(e) {
            						alert("Error!")
            						console.log("ERROR: ", e);
            					}
            				});

            			}

            		function ajaxDelete(id) {
                				var icon = new L.Icon({
                                    iconUrl: ('img/7.png').trim(),
                                    shadowUrl: 'img/marker-shadow.png',
                                    iconSize: [25, 41],
                                    iconAnchor: [12, 41],
                                    popupAnchor: [1, -34],
                                    shadowSize: [41, 41]
                                });
                				$.ajax({
                					type : "POST",
                					contentType : "application/json",
                					url : "map/deleteMarker",
                					data : id,
                					dataType : 'json',
                					success : function(result) {
                						if (result.status == "success") {
                                            alert("success");

                						}
                						console.log(result);
                					},
                					error : function(e) {
                						alert("Error!")
                						console.log("ERROR: ", e);
                					}
                				});
                    ajaxGet();
                    location.reload(true);
                        			}


			var blueIcon = new L.Icon({
            	iconUrl: 'img/marker-icon-2x-blue.png',
            	shadowUrl: 'img/marker-shadow.png',
            	iconSize: [25, 41],
            	iconAnchor: [12, 41],
            	popupAnchor: [1, -34],
            	shadowSize: [41, 41]
            });

            var redIcon = new L.Icon({
            	iconUrl: 'img/marker-icon-2x-red.png',
            	shadowUrl: 'img/marker-shadow.png',
            	iconSize: [25, 41],
            	iconAnchor: [12, 41],
            	popupAnchor: [1, -34],
            	shadowSize: [41, 41]
            });

            var greenIcon = new L.Icon({
            	iconUrl: 'img/marker-icon-2x-green.png',
            	shadowUrl: 'img/marker-shadow.png',
            	iconSize: [25, 41],
            	iconAnchor: [12, 41],
            	popupAnchor: [1, -34],
            	shadowSize: [41, 41]
            });

            var orangeIcon = new L.Icon({
            	iconUrl: 'img/marker-icon-2x-orange.png',
            	shadowUrl: 'img/marker-shadow.png',
            	iconSize: [25, 41],
            	iconAnchor: [12, 41],
            	popupAnchor: [1, -34],
            	shadowSize: [41, 41]
            });

            var yellowIcon = new L.Icon({
            	iconUrl: 'img/marker-icon-2x-yellow.png',
            	shadowUrl: 'img/marker-shadow.png',
            	iconSize: [25, 41],
            	iconAnchor: [12, 41],
            	popupAnchor: [1, -34],
            	shadowSize: [41, 41]
            });

            var violetIcon = new L.Icon({
            	iconUrl: 'img/marker-icon-2x-violet.png',
            	shadowUrl: 'img/marker-shadow.png',
            	iconSize: [25, 41],
            	iconAnchor: [12, 41],
            	popupAnchor: [1, -34],
            	shadowSize: [41, 41]
            });

            var greyIcon = new L.Icon({
            	iconUrl: 'img/marker-icon-2x-grey.png',
            	shadowUrl: 'img/marker-shadow.png',
            	iconSize: [25, 41],
            	iconAnchor: [12, 41],
            	popupAnchor: [1, -34],
            	shadowSize: [41, 41]
            });

            var blackIcon = new L.Icon({
            	iconUrl: 'img/marker-icon-2x-black.png',
            	shadowUrl: 'img/marker-shadow.png',
            	iconSize: [25, 41],
            	iconAnchor: [12, 41],
            	popupAnchor: [1, -34],
            	shadowSize: [41, 41]
            });

		})