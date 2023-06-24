<template>
	<view class="container">
    <!--搜索栏-->
    <u-sticky style="top: 0" offset-top="0">
      <view class="search-wrap">
        <u-search placeholder="搜索关键词" disabled height="32" :show-action="false" @click="handleSearchClick"></u-search>
      </view>
    </u-sticky>
		<v-tabs v-model="current" :tabs="tabs" :scroll="false" @change="changeTab"></v-tabs>
    <!-- 动态 -->
    <view class="context" v-if="current==0">
      <feedBox></feedBox>
    </view>
    <!-- 我的发布 -->
    <view class="context" v-if="current==1">

    </view>
    <!-- 评论我的 -->
    <view class="context" v-if="current==2">

    </view>
    <!-- 点赞 -->
    <view class="context" v-if="current==3">

    </view>
	</view>
</template>

<script>
  import feedBox from "@/components/nongmo-feedbox/nongmo-feedbox.vue"
	export default {
    components: {
      feedBox
    },
		data() {
			return {
        current: 0,
        tabs: ['发现', '我的发布', '评论我的', '点赞']
			}
		},
		methods: {
      //GET方式-请求数据
      getIndex(){
        uni.request({
          url: `${this.$baseUrl}/sns/publish/page`,
          method: 'GET',
          success: (res)=>{
            console.log(res.data);
          },
          fail: (err)=>{}
        })
      },
      //GET方式-请求数据
      getIndex(){
        uni.request({
          url: `${this.$baseUrl}/sns/publish/page`,
          method: 'GET',
          success: (res)=>{
            console.log(res.data);
          },
          fail: (err)=>{}
        })
      },
      changeTab(index) {
        console.log('当前选中的项：' + index)
        this.current = index;
        if (0 == index) {
          this.getIndex()
        }
      }
		},
    onLoad() {
      //this.getIndex();
    }
	}
</script>

<style>

</style>
