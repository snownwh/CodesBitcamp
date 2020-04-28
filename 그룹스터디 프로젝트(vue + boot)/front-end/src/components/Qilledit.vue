
<template>
  <div class="example">
    <quill-editor
      class="editor"
      ref="myTextEditor"
      :value="content"
      :options="editorOption"
      @change="onEditorChange"
      @blur="onEditorBlur($event)"
      @focus="onEditorFocus($event)"
      @ready="onEditorReady($event)"
    />
    <!-- <div class="output code">
      <code class="hljs" v-html="contentCode"></code>
    </div> -->
    <!-- <div class="output ql-snow">
      <div class="ql-editor" v-html="content"></div>
    </div> -->
  </div>
</template>

<script>
  import dedent from 'dedent'
  import hljs from 'highlight.js'
  import debounce from 'lodash/debounce'
  import { quillEditor } from 'vue-quill-editor'

  // highlight.js style
  import 'highlight.js/styles/tomorrow.css'

  // import theme style
  import 'quill/dist/quill.core.css'
  import 'quill/dist/quill.snow.css'

  export default {
    name: 'quill-example-snow',
    title: 'Theme: snow',
    components: {
      quillEditor
    },
    data() {
      return {
        editorOption: {
          modules: {
            toolbar: [
              ['bold', 'italic', 'underline', 'strike'],
              ['blockquote', 'code-block'],
              [{ 'list': 'ordered' }, { 'list': 'bullet' }],
              [{ 'indent': '-1' }, { 'indent': '+1' }],
              [{ 'direction': 'rtl' }],
              [{ 'size': ['small', false, 'large', 'huge'] }],
              [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
              [{ 'color': [] }, { 'background': [] }],
              [{ 'align': [] }],
              ['clean'],
              ['link', 'image']
            ],
            syntax: {
              highlight: text => hljs.highlightAuto(text).value
            }
          }
        },
        content: dedent`
          
        `,
      }
    },
    methods: {
      onEditorChange: debounce(function(value) {
        this.content = value.html
        let content1 = this.content
        this.$emit('contentS', content1)
        return content1
      }, 466),
      onEditorBlur(editor) {
        console.log('editor blur!', editor)
      },
      onEditorFocus(editor) {
        console.log('editor focus!', editor)
      },
      onEditorReady(editor) {
        console.log('editor ready!', editor)
      }
    },
    computed: {
      editor() {
        return this.$refs.myTextEditor.quill
      },
      contentCode() {
        return hljs.highlightAuto(this.content).value
      }
    },
    mounted() {
      console.log('this is Quill instance:', this.editor)
      
    }
  }
</script>

<style lang="scss" scoped>

  .example {
    display: flex;
    flex-direction: column;

    .editor {
      height: 40rem;
      overflow: hidden;
    }

    .output {
      width: 100%;
      height: 20rem;
      margin: 0;
      border: 1px solid #ccc;
      overflow-y: auto;
      resize: vertical;

      &.code {
        padding: 1rem;
        height: 16rem;
      }

      &.ql-snow {
        border-top: none;
        height: 24rem;
      }

      
    }
  }
</style>


<!-- <template>
    <div class="qilledit">
        <quill-editor
            ref="myQuillEditor"
            v-model="content"
            :options="editorOption"
            @blur="onEditorBlur($event)"
            @focus="onEditorFocus($event)"
            @ready="onEditorReady($event)"
        />
    </div>
</template> 

<script>
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

import { quillEditor } from 'vue-quill-editor'
export default {
    components: {
        quillEditor
    },
    data(){
        return {
            content: '',
            editorOption: {
            }
        }
    },
    methods: {
        onEditorBlur(quill) {
            console.log('editor blur!', quill)
        },
        onEditorFocus(quill) {
            console.log('editor focus!', quill)
        },
        onEditorReady(quill) {
            console.log('editor ready!', quill)
        },
        onEditorChange({ quill, html, text }) {
            console.log('editor change!', quill, html, text)
            this.content = html
        }
    },
    computed: {
        editor() {
            return this.$refs.myQuillEditor.quill
        }
    },
    mounted() {
        console.log('this is current quill instance object', this.editor)
    }
}
</script>

<style>

</style> -->