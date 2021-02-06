const GradjaninPage = Vue.component('gradjanin-page', {
    template: `
    <requests-table-page-component></requests-table-page-component>
    `,
     mounted() {
        if (!localStorage.getItem('currentUser'))
             this.$router.push({ path: '/' });

         //location.reload();
     }
})