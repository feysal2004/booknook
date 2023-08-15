<template>
  <div class="map-container">
    <h2 class="map-header">Check Out Local Bookstores!</h2>
 
    <gmap-map
      :center="center"
      :zoom="10"
      style="width:100%;  height: 350px;">
      <gmap-marker :key="index" v-for="(gmp, index) in locations" :position="gmp" :title="gmp.label" @click="openGoogleMaps(gmp)"></gmap-marker>
    </gmap-map>
 
  </div>
</template>
 
<script>
export default {
  name: "AddGoogleMap",
  data() {
    return {
      center: { 
          lat: 39.7837304, 
          lng: -100.4458825 
      },
      locations: [],
      currentLocation: null
    };
  },
 
  mounted() {
    this.setLocationLatLng();
  },
 
  methods: {
    setPlace(loc) {
      this.currentLocation = loc;
    },
    setLocationLatLng: function() {
        navigator.geolocation.getCurrentPosition(geolocation => {
          this.center = {
            lat: geolocation.coords.latitude,
            lng: geolocation.coords.longitude
          };
        });
 
        this.locations = [
          {
              lat: 40.034145945564255, 
              lng: -83.01676600141941,
              label: 'Karen Wickliff-Books',
              address: '3527 N High St, Columbus, OH 43214'
          },
          {
              lat: 40.04805351691305, 
              lng: -83.0205286835814,
              label: 'Hoffman Books',
              address: '4167 N High St, Columbus, OH 43214'
          },
          {
              lat: 40.02682787761025, 
              lng: -83.01383388769464,
              label: 'Phoenix Books Ltd',
              address: '3110 N High St, Columbus, OH 43202'
          },
          {
              lat: 39.996080947872954, 
              lng: -83.02751699258819,
              label: 'Barnes & Noble',
              address: '1739 Olentangy River Rd, Columbus, OH 43212'
          },
          {
              lat: 40.03579788663061, 
              lng: -82.99857823121397,
              label: 'Dwell Study Center',
              address: '620 E Oakland Park Ave, Columbus, OH 43214'
          },
          {
              lat: 40.05073440810323, 
              lng: -83.0201662539624, 
              label: 'The Laughing Ogre',
              address: '4258 N High St, Columbus, OH 43214'
          },
          {
              lat: 40.06839933963786, 
              lng: -83.08836820846554,  
              label: 'Half Price Books',
              address: '561 S State St, Westerville, OH 43081'
          },
          {
              lat: 40.02427808076607,  
              lng: -83.00796696190108,  
              label: 'Bookspace Columbus',
              address: '200 Crestview Rd, Columbus, OH 43202'
          },
          {
              lat: 39.99457812202963,  
              lng: -83.00658630610913,   
              label: 'The Ohio State University Bookstore',
              address: '1598 N High St, Columbus, OH 43201'
          },
          {
              lat: 39.99088789114035,   
              lng: -83.05486830052685,  
              label: 'Serenity Book Shop',
              address: '1806 W 5th Ave, Columbus, OH 43212'
          },
          {
              lat: 39.9587186457828,   
              lng: -82.93455455558639,  
              label: 'Gramercy Books',
              address: '2424 E Main St, Bexley, OH 43209'
          },
          {
              lat: 39.99477598351759,    
              lng: -82.9417502488406,  
              label: 'Barnes & Noble at Ohio Dominican University',
              address: '1191 Sunbury Rd, Columbus, OH 43219'
          },
      ];
 
    },
    openGoogleMaps(location) {
      const address = location.address;
      const label = label;

      const googleMapsLink = `https://www.google.com/maps/place/${address}`;

      window.open(googleMapsLink, "_blank");
    }
  }
};
</script>

<style scoped>
.map-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: flex-start;
  padding: 1px;
  border-radius: 10px;
  width: 60%;
  height: auto;
  background-size: auto;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin: 20px auto;
}

.gmap-map {
  border: 1px solid #ccc;
  border-radius: 10px;
  width: 100%;
  height: 350px;
  margin-top: 10px;
}

.map-header {
  text-align: center;
  font-family: Arial, sans-serif;
  font-size: 20px;
  color: #333;
  margin-bottom: 10px;
}
</style>