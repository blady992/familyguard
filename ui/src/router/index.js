import Vue from 'vue';
import Router from 'vue-router';
import LandingPage from '@/components/LandingPage';
import PersonPage from '@/components/people/PersonPage';
import PeopleListPage from '@/components/people/PeopleListPage';
import EventListPage from '@/components/events/EventListPage';
import EventPage from '@/components/events/EventPage';

Vue.use(Router);

export default new Router({
  routes: [
    {
      path: '/',
      name: 'LandingPage',
      component: LandingPage,
    },
    {
      path: '/people/:id',
      name: 'PersonPage',
      component: PersonPage,
      props: true,
    },
    {
      path: '/people/_new',
      name: 'NewPersonPage',
      component: PersonPage,
    },
    {
      path: '/people',
      name: 'PeopleListPage',
      component: PeopleListPage,
    },
    {
      path: '/events',
      name: 'EventListPage',
      component: EventListPage,
    },
    {
      path: '/events/:id',
      name: 'EventPage',
      component: EventPage,
      props: true,
    },
  ],
});
