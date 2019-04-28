import Vue from 'vue';
import Router from 'vue-router';
import LandingPage from '@/components/LandingPage';
import PersonPage from '@/components/people/PersonPage';
import PeopleListPage from '@/components/people/PeopleListPage';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'LandingPage',
      component: LandingPage,
    },
    {
      path: '/person',
      name: 'PersonPage',
      component: PersonPage,
    },
    {
      path: '/people',
      name: 'PeopleListPage',
      component: PeopleListPage,
    },
  ],
});
