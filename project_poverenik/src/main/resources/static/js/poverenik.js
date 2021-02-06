const PoverenikPage = Vue.component('poverenik-page', {
    template: `

    `,
     mounted() {
     if (!localStorage.getItem('currentUser'))
           this.$router.push({ path: '/' });

         //location.reload();
     }
})