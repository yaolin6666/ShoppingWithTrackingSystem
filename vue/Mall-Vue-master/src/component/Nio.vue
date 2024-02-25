<template>
  <div>
    <header>dsdssssssssss</header>
    <article>dsds</article>
  {{pan }}
  <div class="kk"><img src="@/assets/hb.png" width="500px" height="500px" alt="">
  <div class="kk1">111111</div>
  <!-- <div class="jj">sdsd</div> -->
  </div>
  <!-- <div class="rr">
  <ul>
    <li>
       sdsd
    </li>
    <li>
      dsd
    </li>
  </ul>
  </div> -->

  </div>
</template>

<script>
export default {
  name: 'MallVueMasterNio',

  data() {
    return {
      pan:"pan",
      pan1:"1"
    };
  },

  mounted() {

  },

  methods: {

  },
};
</script>

<style  scoped>
.kk{
  position:absolute;
  border: 1px solid red;

}
.kk .jj{
  position: relative;
  top: -150px;
  border: 1px solid red;
}
.rr li:first-child{
  color: red;
}
header{
  background: blue;
}
article{
  background: brown;
}
.kk1::after{
  content: "";
  position: relative;
 top: -200px;

  width: 200px;
  height: 200px;

  background: url(../assets/1.jpg) no-repeat center;

}
.kk1:hover::after{
  display: block;

  color: black;
}
</style>
