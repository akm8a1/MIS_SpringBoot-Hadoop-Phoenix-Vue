import Vue from 'vue';
import Router from 'vue-router';

Vue.use(Router);

export default new Router({
    routes: [
        {
            path: '/',
            redirect: '/dashboard'
        },
        {
            path: '/',
            component: () => import('../components/common/Home.vue'),
            meta: { title: '自述文件' },
            children: [
                {
                    path: '/dashboard',
                    component: () => import('../components/page/Dashboard.vue'),
                    meta: { title: '系统首页' }
                },
                {
                    path: '/base',
                    component: () => import('../components/page/table/BaseTable.vue'),
                    meta: { title: '球员概览' }
                },
                {
                    path: '/performance',
                    component: () => import('../components/page/table/Performance.vue'),
                    meta: { title: '球员详细数据' }
                },
                {
                    path: '/advanced',
                    component: () => import('../components/page/table/Advanced.vue'),
                    meta: { title: '球员高阶数据' }
                },
                {
                    path: '/teams',
                    component: () => import('../components/page/table/TeamTable.vue'),
                    meta: { title: '球队数据' }
                },
                {
                    path: '/notes',
                    component: () => import('../components/page/Tabs.vue'),
                    meta: { title: 'tab选项卡' }
                },
                {
                    path: '/form',
                    component: () => import('../components/page/BaseForm.vue'),
                    meta: { title: '基本表单' }
                },
                {
                    // 图片上传组件
                    path: '/upload',
                    component: () => import('../components/page/Upload.vue'),
                    meta: { title: '文件上传' }
                },
                {
                    // vue-schart组件
                    path: '/charts',
                    component: () => import('../components/page/BaseCharts.vue'),
                    meta: { title: '联盟数据可视化' }
                },
                {
                    path: '/404',
                    component: () => import('../components/page/404.vue'),
                    meta: { title: '404' }
                },
                {
                    path: '/403',
                    component: () => import('../components/page/403.vue'),
                    meta: { title: '403' }
                },
                {
                    path: '/playerChart',
                    component: () => import('../components/page/PlayInfoChart.vue'),
                    meta: { title: 'playerChart' }
                },
            ]
        },
        {
            path: '*',
            redirect: '/404'
        }
    ]
});
