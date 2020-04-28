<template>
  <div id="app">
     <atom-spinner
          :animation-duration="1000"
          :size="60"
          :color="'#ff1d5e'"
     />
  </div>
</template>

<script>
  // To use minified css and js files instead of .vue single file components:
  // import 'epic-spinners/dist/lib/epic-spinners.min.css'
  // import {AtomSpinner} from 'epic-spinners/dist/lib/epic-spinners.min.js'
  
  // To get tree shaking from webpack (won't import all spinners when you only need one)
  // import AtomSpinner from 'epic-spinners/src/components/lib/AtomSpinner'
  
  import {AtomSpinner} from 'epic-spinners'
  export default {
    components: {
      AtomSpinner
    }
  }
</script>