const ResolutionTablePage = Vue.component("resolution-table-page-component", {
    data () {
        return {
            complaints: []
        }
    },
    template: `
    <div>
        <table class="display-table">
            <tr>
                <th>Sifra resenja</th>
                <th colspan="2" class="text-center">Preuzimanje dokumenta</th>
                <th colspan="2" class="text-center">Preuzimanje metapodataka</th>
            </tr>
            <tr v-for="item in complaints">
                <td>{{ item }}</td>
                <td><a v-bind:href="'api/solution/xhtml/' + item" target="_blank">XHTML</a></td>
                <td><a v-bind:href="'api/solution/pdf/' + item" target="_blank">PDF</a></td>
                <td><a v-bind:href="'api/solution/rdf/' + item" target="_blank">RDF</a></td>
                <td><a v-bind:href="'api/solution/json/' + item" target="_blank">JSON</a></td>
            </tr>
        </table>
    </div>
    `,
    mounted() {
    var currentRole = JSON.parse(localStorage.getItem('currentUser')).roles;
        var token = JSON.parse(localStorage.getItem('currentUser')).token;
        var self = this;
        if(currentRole == "ROLE_POVERENIK"){
    axios.get("/api/solution", {headers: {'Content-Type': 'application/xml', 'Authorization' : 'Bearer ' + token}}).then(
                response => {
                    //alert('Zahtev uspesno primljen. Dobicete odgovor od poverenika putem elektronske poste.');
                    xmlDoc = $.parseXML(response.data);
                    console.log(xmlDoc);
                    var self = this;
                    $(xmlDoc).find('complaint').each(function(){
                         self.complaints.push($(this).text());
                    });
                },
                error => {
                    alert('Doslo je do greske prilikom slanja zahteva.');
                });
    }else{
         axios.get("/api/solution/user" , {headers: {'Content-Type': 'application/xml', 'Authorization' : 'Bearer ' + token}}).then(
                         response => {
                             //alert('Zahtev uspesno primljen. Dobicete odgovor od poverenika putem elektronske poste.');
                             xmlDoc = $.parseXML(response.data);
                             results = $(xmlDoc).find('result');
                             console.log(response.data)
                             $(results).each(function(){
                                 requestUri = $(this).find('[name="subject"]').find('uri').text();
                                 self.complaints.push(requestUri.substring(requestUri.lastIndexOf("/") + 1) + ".xml")
                             });
                         },
                         error => {
                             alert('Doslo je do greske prilikom slanja zalbe na odluku.');
                         });

         }
         }
})