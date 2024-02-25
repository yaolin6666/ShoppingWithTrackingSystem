<template>

    <div class="" id="box" >
		<ul v-for="blog in products" :key="blog">
			<li  >

				<h4>{{blog.title}}</h4>
				<p>{{blog.id}}</p>
			</li>
		</ul>



  </div>

</template>

<script>
import { mapState, mapActions } from 'vuex'
import store from '@/vuex/store';
export default {
  name: 'ProductList',
   data() {
    return {
      products:[],
      search:""


    }

    },
  computed: {

    ...mapState('products')
  },
  methods: {

    ...mapActions("getAllProducts")
  },
  created () {
    const  _this = this
    this.$axios.get('http://jsonplaceholder.typicode.com/posts').then(function (resp) {
        _this.products = resp.data.slice(0,10);

        console.log(resp);
      }),
    this.getAllProducts()
  },
  store
}
</script>


<style>
	#box ul{
		display: flex;
		flex-wrap: wrap;
	}
	#box li{
		padding: 3px;
		list-style: none;
		margin-right: 15px;
		border: 1px solid #eee;
	}
	#box img{
		width: 200px;
		height: 150px;
	}


