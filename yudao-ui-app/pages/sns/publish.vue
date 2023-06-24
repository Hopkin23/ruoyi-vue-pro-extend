<template>
	<view class="container">
    <!--搜索栏-->
    <u-sticky style="top: 0" offset-top="0">
      <view class="search-wrap">
        <u-search placeholder="搜索关键词" disabled height="32" :show-action="false" @click="handleSearchClick"></u-search>
      </view>
    </u-sticky>
		<v-tabs v-model="current" :tabs="tabs" :scroll="false" @change="changeTab"></v-tabs>
	</view>
</template>

<script>
	export default {
		data() {
			return {
        current: 0,
        tabs: ['发现', '我的发布', '评论我的', '点赞']
			}
		},
		methods: {
      changeTab(index) {
        console.log('当前选中的项：' + index)
      }
		}
	}
</script>

<style>

</style>
