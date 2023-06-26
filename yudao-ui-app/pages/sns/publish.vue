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
      <mescroll-body @init="mescrollInit" @down="downCallback" @up="upCallback">
        <view v-for="(item,index) in publishList" :key="index">
          <!-- main控制总体布局 -->
          <view class="main">
            <view class="top">
              <view class="avatar">
                <u-avatar size="50" :src="item.user.avatar"></u-avatar>
              </view>
              <!-- 姓名和日期 -->
              <view class="name-date">
                <view class="name">{{
                  item.user.username
                }}
                </view>
                <view class="date">{{ item.createTime }}</view>
              </view>
            </view>

            <!-- 文字内容 -->
            <view class="content">{{ item.content }}</view>

            <!-- 图片内容 -->
            <view class="pic">
              <u-image v-for="(item,index) in item.picUrls" :key="index"
                mode="heightFix"
                @click="previewImage"
                :src="item"
              ></u-image>
            </view>

            <!-- 底部赞和评论 -->
            <view class="bottom">
              <u-icon size="40" name="chat" @click="goDetail" :label="item.commentCount"></u-icon>
              <u-icon
                class="u-m-l-60"
                size="40"
                @click="upClick"
                :color="upColor"
                name="thumb-up"
                :label="item.likeCount"
              ></u-icon>
            </view>
          </view>
        </view>
      </mescroll-body>
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
  // 引入mescroll-mixins.js
  import MescrollMixin from "@/uni_modules/mescroll-uni/components/mescroll-uni/mescroll-mixins.js";

  export default {
    mixins: [MescrollMixin], // 使用mixin
		data() {
			return {
        pageNo: 0,
        pageSize: 2,
        upColor: '',
        current: 0,
        tabs: ['发现', '我的发布', '评论我的', '点赞'],
        publishList: [],
        data: {}
			}
		},
		methods: {
      mescrollInit(mescroll) {
        this.mescroll = mescroll;
      },
      /*上拉加载的回调*/
      upCallback(pageNo, pageSize) {
      	uni.request({
      	  url: `${this.$baseUrl}/sns/publish/page?pageNo=` + pageNo +'&pageSize=' + pageSize,
      	  method: 'GET',
      	  success: (res)=>{
      	    console.log(res);
      	    this.publishList = res.data.data.list;
            this.mescroll.endSuccess(res.data.data.list.length);
            //设置列表数据
            if(pageNo == 1) this.publishList = []; //如果是第一页需手动制空列表
            this.publishList = this.publishList.concat(res.data.data.list); //追加新数据
      	  },
      	  fail: (err)=>{}
      	})
      },

      //GET方式-请求数据
      getIndex(pageNo, pageSize) {
        uni.request({
          url: `${this.$baseUrl}/sns/publish/page?pageNo=` + pageNo +'&pageSize=' + pageSize,
          method: 'GET',
          success: (res)=>{
            console.log(res);
            // console.log(JSON.stringify(res.data))
            // console.log(JSON.stringify(res.data))
            this.publishList = res.data.data.list
            //console.log(this.publishList);
            //console.log(this.publishList)
            return res.data;
          },
          fail: (err)=>{}
        })
      },
      changeTab(index) {
        console.log('当前选中的项：' + index)
        this.current = index;
        if (0 == index) {
          this.$refs.paging.reload();
        }
      }
		},
    onLoad() {
      // this.queryList(this.pageNo, this.pageSize);
    }
	}
</script>


<style lang="scss" scoped>
  .main {
    background: #ffffff;
    padding: 30rpx 20rpx 40rpx 20rpx;
  }
  .top {
    display: flex;
    flex-direction: row; // 设置水平布局
  }
  .name {
    font-weight: 200;
  }
  .bottom {
    justify-content: space-between;
    display: flex;
    flex-direction: row; // 设置水平布局
  }
  .pic {
    display: flex;
    flex-direction: row; // 设置水平布局
  }
</style>
