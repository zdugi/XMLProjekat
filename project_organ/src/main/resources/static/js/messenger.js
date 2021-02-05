const Messenger = Vue.component('messenger', {
    data() {
        return {
            msg: '',
            messages: [

            ]
        }
    },
    template: `
    <div class="messenger">
        <div class="mwrap">
            <h3>Poruke</h3>
            <div class="messages-stack">
                <div v-for="message in messages">[{{ timeConverter(message.time) }}] {{ message.body }}</div>
            </div>
            <div class="messenger-input">
                <input v-on:keyup.enter="send()" v-model="msg" type="text" placeholder="Posaljite poruku povereniku.." />
            </div>
        </div>
    </div>
    `,
    methods: {
        send() {
            var xmlBody = '<message ' +
                          'body="' + this.msg + '" ' +
                          '/>';
            axios.post('/api/message', xmlBody, { headers: {'Content-Type': 'application/xml'} }).then(
            response => {
                var xmlDoc = $.parseXML(response.data);
                var body = $(xmlDoc).find('message').attr('body');
                var time = $(xmlDoc).find('message').attr('time');
                this.messages.unshift({body: body, time: time})
                this.msg = '';
            });
        },
        timeConverter(UNIX_timestamp){
          var a = new Date(UNIX_timestamp * 1000);
          var months = ['Jan','Feb','Mar','Apr','May','Jun','Jul','Aug','Sep','Oct','Nov','Dec'];
          var year = a.getFullYear();
          var month = months[a.getMonth()];
          var date = a.getDate();
          var hour = a.getHours();
          var min = a.getMinutes();
          var sec = a.getSeconds();

          if (month < 10) month = '0' + month;
          if (date < 10) date = '0' + date;
          if (hour < 10) hour = '0' + hour;
          if (min < 10) min = '0' + min;
          if (sec < 10) sec = '0' + sec;

          var time = date + ' ' + month + ' ' + year + ' ' + hour + ':' + min + ':' + sec ;
          return time;
        }
    },
    mounted() {
        axios.get('/api/message', { headers: {'Content-Type': 'application/xml'} }).then(
            response => {
                var dat = $.parseXML(response.data);
                console.log(dat);
                var self = this;
                $(dat).find('messages').each(function() {
                    var body = $(this).attr('body');
                    var time = $(this).attr('time');
                    self.messages.unshift({body: body, time: time})
                });
            }
        );
    }
});