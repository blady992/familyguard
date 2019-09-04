<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <router-link to="/" class="navbar-brand">Familyguard</router-link>
    <ul class="navbar-nav">
      <li class="nav-item">
        <router-link to="/people" class="nav-link">People</router-link>
      </li>
      <li class="nav-item">
        <router-link to="/events" class="nav-link">Events</router-link>
      </li>
    </ul>
  </nav>
</template>

<script>
export default {
  name: 'Navbar',
};
</script>

<style scoped>

</style>
