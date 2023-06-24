<template name="feedBox">
  <view>
    <!-- main控制总体布局 -->
    <view class="main">
      <view v-for="(item,index) in publishList" key="id">
        <view class="top">
          <view class="avatar">
            <u-avatar size="50" :src="item.avatar"></u-avatar>
          </view>
          <!-- 姓名和日期 -->
          <view class="name-date">
            <view class="name">{{
              item.username
            }}
            </view>
            <view class="date">{{ item.date }}</view>
            <u-icon
              v-if="showDeleteIcon"
              @click="deleteRecord"
              size="40"
              name="trash"
            ></u-icon>
          </view>
        </view>

        <!-- 文字内容 -->
        <view class="content">{{ item.content }}</view>

        <!-- 图片内容 -->
        <view class="pic">
          <u-image
            width="100%"
            height="300rpx"
            @click="previewImage"
            :src="item.image"
          ></u-image>
        </view>

        <!-- 底部赞和评论 -->
        <view class="bottom">
          <u-icon size="40" name="chat" @click="goDetail" label="4865"></u-icon>
          <u-icon
            class="u-m-l-60"
            size="40"
            @click="upClick"
            :color="upColor"
            name="thumb-up"
            label="4865"
          ></u-icon>
        </view>
      </view>
      <u-action-sheet
        :list="list"
        v-model="show"
        :tips="tips"
        :cancel-btn="true"
      ></u-action-sheet>
    </view>
  </view>
</template>

<script>
  export default {
    name: 'feedBox',
    props: {
      showDeleteIcon: {
        type: Boolean,
        default: false,
      },
      allowGoDetail: {
        type: Boolean,
        default: true,
      }
      // item: {
      //   type: Object,
      //   default: {
      //     username: '顾艺馨',
      //     avatar:
      //       'https://img0.baidu.com/it/u=2322326169,1418475405&fm=11&fmt=auto&gp=0.jpg',
      //     content: '今天的数学题, 有点难度, 不过终于解答出来了, 大家一起加油.',
      //     image:
      //       'https://wx1.sinaimg.cn/mw600/71db2511ly1gqcn92klluj21zi2z9npe.jpg',
      //     date: '2021-06-01',
      //   },
      // },

    },
    data() {
      return {
        upColor: '',
        list: [
          {
            text: '不删除',
            color: '#FC6B00',
          },
          {
            text: '删除',
          },
        ],
        publishList: [{
          username: '顾艺馨',
          avatar:
            'https://img0.baidu.com/it/u=2322326169,1418475405&fm=11&fmt=auto&gp=0.jpg',
          content: '今天的数学题, 有点难度, 不过终于解答出来了, 大家一起加油.',
          image:
            'https://wx1.sinaimg.cn/mw600/71db2511ly1gqcn92klluj21zi2z9npe.jpg',
          date: '2021-06-01',
        }],
        show: false,
        tips: {
          text: '是否删除该条动态?',
          color: '#909399',
          fontSize: 24,
        },
      }
    },
    methods: {
      goWrite() {
        uni.navigateTo({
          url: `/pages/index/write`,
        })
      },
      goDetail() {
        if (!this.allowGoDetail) return
        uni.navigateTo({
          url: `/pages/index/detail`,
        })
      },
      upClick() {
        if (this.upColor === '') {
          this.upColor = '#FC6B00'
        } else {
          this.upColor = ''
        }
      },
      previewImage(event) {
        wx.previewImage({
          urls: [
            'https://wx1.sinaimg.cn/mw600/71db2511ly1gqcn92klluj21zi2z9npe.jpg',
          ],
        })
      },
      deleteRecord() {
        this.show = true
      },
    },
  }
</script>

<style lang="scss">
  page {
    background-color: #f5f5f5;
    line-height: 180%;
  }
</style>

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
</style>
