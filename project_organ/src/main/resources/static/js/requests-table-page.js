const RequestsTablePage = Vue.component("requests-table-page-component", {
    data () {
        return {
            requests: [],
            userRole: ""
        }
    },
    template: `
    <div>
        <table class="display-table">
            <tr>
                <th>Sifra zahteva</th>
                <th colspan="2" class="text-center">Preuzimanje dokumenta</th>
                <th colspan="2" class="text-center">Preuzimanje metapodataka</th>
                <th/><th/>
            </tr>
            <tr v-for="item in requests">
                <td>{{ item }}</td>
                <td><a v-bind:href="'/api/requests/xhtml/' + item" target="_blank">XHTML</a></td>
                <td><a v-bind:href="'/api/requests/pdf/' + item" target="_blank">PDF</a></td>
                <td><a v-bind:href="'/api/requests/rdf/' + item" target="_blank">RDF</a></td>
                <td><a v-bind:href="'/api/requests/json/' + item" target="_blank">JSON</a></td>
                <td v-if="userRole == 'ROLE_OFFICIAL'"><button v-on:click = "create('/official/create-response/' + item)" >Dodaj obavestenje</button></td>
                <td v-if="userRole == 'ROLE_OFFICIAL'"><button v-on:click="odbij(item)">Odbij zahtev</button></td>
            </tr>
        </table>
    </div>
    `,
    mounted() {
    this.userRole = JSON.parse(localStorage.getItem('currentUser')).roles;
    console.log(this.userRole);
    axios.get("/api/requests", {headers: {'Content-Type': 'application/xml'}}).then(
                response => {
                    //alert('Zahtev uspesno primljen. Dobicete odgovor od poverenika putem elektronske poste.');
                    xmlDoc = $.parseXML(response.data);
                    console.log(xmlDoc);
                    var self = this;
                    $(xmlDoc).find('resource').each(function(){
                         self.requests.push($(this).text());
                    });
                },
                error => {
                    alert('Ne postoje instance zahteva u bazi.');
                });
    },
    methods : {
        odbij : function(item){
            axios.post('/api/requests/decline/'+item, {headers: {'Content-Type': 'application/xml'}}).then(
            response => {
                alert('Zahtev je uspesno odbijen');
            },
            error => {
                alert('Doslo je do greske.');
            });
        },
        create : function(link){
            console.log(link);
            this.$router.push(link);
        }
    }
})