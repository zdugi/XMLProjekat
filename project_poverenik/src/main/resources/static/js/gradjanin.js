const GradjaninPage = Vue.component('gradjanin-page', {
    template: `

    `,
     mounted() {
        if (!localStorage.getItem('currentUser'))
             this.$router.push({ path: '/' });

         //location.reload();
     }
})