const KreirajResenje = Vue.component('kreiraj-resenje', {
    template: `
    <div style="margin-top: 50px">
        <h5>Odaberite zalbu na koju zelite da kreirate resenje: </h5>
        <button v-on:click="cutanje = !cutanje">Zalbe na cutanje</button><span style="width:50px;"></span>
        <button v-on:click="cutanje = !cutanje">Zalbe na odluku</button>
        <requests-table-page-component v-if="cutanje"></requests-table-page-component>
        <complaint-res-table-page-component v-if="!cutanje"></complaint-res-table-page-component>
    </div>
    `,
    data () {
        return {
            cutanje: true
        }
    },
     mounted() {
        if (!localStorage.getItem('currentUser'))
             this.$router.push({ path: '/' });

         //location.reload();
     }
})