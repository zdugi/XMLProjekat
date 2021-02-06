const PoverenikPage = Vue.component('poverenik-page', {
    template: `<div>
    <messenger></messenger>
    </div>
    `,
     mounted() {
     if (!localStorage.getItem('currentUser'))
           this.$router.push({ path: '/' });

         //location.reload();
     }
})