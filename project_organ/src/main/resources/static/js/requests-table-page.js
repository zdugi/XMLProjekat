const RequestsTablePage = Vue.component("requests-table-page-component", {
    data () {
        return {
            requests: []
        }
    },
    template: `
    <div>
        <table class="display-table">
            <tr>
                <th>Sifra zahteva</th>
                <th colspan="2" class="text-center">Preuzimanje dokumenta</th>
                <th colspan="2" class="text-center">Preuzimanje metapodataka</th>
            </tr>
            <tr v-for="item in requests">
                <td>{{ item }}</td>
                <td><a v-bind:href="'/api/requests/xhtml/' + item" target="_blank">XHTML</a></td>
                <td><a v-bind:href="'/api/requests/pdf/' + item" target="_blank">PDF</a></td>
                <td><a v-bind:href="'/api/requests/rdf/' + item" target="_blank">RDF</a></td>
                <td><a v-bind:href="'/api/requests/json/' + item" target="_blank">JSON</a></td>
            </tr>
        </table>
    </div>
    `,
    mounted() {
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
                    alert('Doslo je do greske prilikom slanja zahteva.');
                });
    }
})