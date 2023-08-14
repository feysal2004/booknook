<template>
  <div class="google-maps">
    <div>
      <h2>Books In Your Area!</h2>
      <label>
        <gmap-autocomplete @place_changed="initMarker"></gmap-autocomplete>
        <button @click="addLocationMarker">Add</button>
      </label>
      <br/>
 
    </div>
    <br>
    <gmap-map
        :zoom="10"    
        :center="center"
        style="width:100%;  height: 600px;"
        ref="gmap" 
      >
      <gmap-marker
        :key="index"
        v-for="(m, index) in locationMarkers"
        :position="m.position"
        @click="center=m.position"
      ></gmap-marker>
    </gmap-map>
  </div>
</template>
 
<script>
import axios from 'axios';
// import AddGoogleMap from "../components/AddGoogleMap.vue"

export default {
  name: "AddGoogleMap",
  data() {
    return {
      center: {
        lat: 39.7837304,
        lng: -100.4458825
      },
      locationMarkers: [],
      locPlaces: [],
      nearbyBookstores: [],
      existingPlace: null,
      maps: []
    };
  },

  mounted() {
    this.locateGeoLocation();
    this.fetchNearbyBookstores();
  },

  methods: {
    initMarker(loc) {
      this.existingPlace = loc;
      this.fetchNearbyBookstores();
    },
    addLocationMarker() {
      if (this.existingPlace) {
        const marker = {
          lat: this.existingPlace.geometry.location.lat(),
          lng: this.existingPlace.geometry.location.lng()
        };
        this.locationMarkers.push({ position: marker });
        this.locPlaces.push(this.existingPlace);
        this.center = marker;
        this.existingPlace = null;
      }
    },
    locateGeoLocation: function() {
      navigator.geolocation.getCurrentPosition(res => {
        this.center = {
          lat: res.coords.latitude,
          lng: res.coords.longitude
        };
      });
    },

    async fetchNearbyBookstores() {
      try {
        const response = await axios.get(
          "https://maps.googleapis.com/maps/api/js?key=AIzaSyCRUi9iFNf5yy3HxMkPiy6dwVCe1EC0q8g&libraries=places/" 
        );
        this.nearbyBookstores = response.data.maps(store => ({
          position: { lat: store.lat, lng: store.lng },
        }));
      } catch (error) {
        console.error("Error fetching nearby bookstores:", error);
      }
    },
    // created() {
    //   this.fetchNearbyBookstores();
    // }
  }
};
</script>

<style>
.map {
    width: 75%;
    height: auto;
}
</style>