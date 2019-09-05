<template>
  <div id="app">
    <navbar></navbar>
    <router-view/>
  </div>
</template>

<script>
import Navbar from './components/Navbar';

export default {
  name: 'App',
  components: {
    Navbar,
  },
};
</script>

<style>
@font-face {
  font-family: 'astloch';
  src: url('/static/fonts/astloch-bold-webfont.woff2') format('woff2'),
  url('/static/fonts/astloch-bold-webfont.woff') format('woff');
  font-weight: bold;
  font-style: normal;
}

@font-face {
  font-family: 'astloch';
  src: url('/static/fonts/astloch-regular-webfont.woff2') format('woff2'),
  url('/static/fonts/astloch-regular-webfont.woff') format('woff');
  font-weight: normal;
  font-style: normal;
}
</style>
