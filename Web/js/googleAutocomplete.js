var placeSearch, autocomplete;

var componentForm = {
  street_number: 'short_name',
  route: 'long_name',
  locality: 'long_name',
  administrative_area_level_1: 'short_name',
  country: 'long_name',
  postal_code: 'short_name'
};

function inicializarDireccion(elementId) {

  // Create the autocomplete object, restricting the search
  // to geographical location types.
  autocomplete = new google.maps.places.Autocomplete(
      /** @type {HTMLInputElement} */(document.getElementById(elementId)),
      { types: ['geocode'] });
  // When the user selects an address from the dropdown,
  // populate the address fields in the form.
  google.maps.event.addListener(autocomplete, 'place_changed', function() {
  
  });

}

function inicializarCiudad(elementId) {

  var input = document.getElementById(elementId);
  var autocomplete = new google.maps.places.Autocomplete(input);  

  // These are my options for the AutoComplete
  autocomplete.setTypes(['(cities)']);
  //autocomplete.setComponentRestrictions({'country': 'es'});

  google.maps.event.addListener(autocomplete, 'place_changed', function() {
      result = autocomplete.getPlace();
      if(typeof result.address_components == 'undefined') {
          // The user pressed enter in the input 
          // without selecting a result from the list
          // Let's get the list from the Google API so that
          // we can retrieve the details about the first result
          // and use it (just as if the user had actually selected it)
          autocompleteService = new google.maps.places.AutocompleteService();
          autocompleteService.getPlacePredictions(
              {
                  'input': result.name,
                  'offset': result.name.length,
                  // I repeat the options for my AutoComplete here to get
                  // the same results from this query as I got in the 
                  // AutoComplete widget
                  'componentRestrictions': {'country': 'es'},
                  'types': ['(cities)']
              },
              function listentoresult(list, status) {
                  if(list == null || list.length == 0) {
                      // There are no suggestions available.
                      // The user saw an empty list and hit enter.
                      console.log("No results");
                  } else {
                      // Here's the first result that the user saw
                      // in the list. We can use it and it'll be just
                      // as if the user actually selected it
                      // themselves. But first we need to get its details
                      // to receive the result on the same format as we
                      // do in the AutoComplete.
                      placesService = new google.maps.places.PlacesService(document.getElementById('placesAttribution'));
                      placesService.getDetails(
                          {'reference': list[0].reference},
                          function detailsresult(detailsResult, placesServiceStatus) {
                              // Here's the first result in the AutoComplete with the exact
                              // same data format as you get from the AutoComplete.
                              console.log("We selected the first item from the list automatically because the user didn't select anything");
                              console.log(detailsResult);
                          }
                      );
                  }
              }
          );
      } else {
          // The user selected a result from the list, we can 
          // proceed and use it right away
          console.log("User selected an item from the list");
          console.log(result);
      }
  });

}