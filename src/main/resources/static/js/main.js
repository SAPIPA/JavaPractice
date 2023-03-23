function getIndex(list, id) {
    for (var i = 0; i < list.length; i++ ) {
        if (list[i].id === id) {
            return list[i].id;
        }
    }

    return -1;
}


var phototechApi = Vue.resource('/phototech{/id}');

Vue.component('phototech-form', {
    props: ['phototechs', 'phototechAttr'],
    data: function() {
        return {
            serial_number: '',
            id: ''
        }
    },
    watch: {
        phototechAttr: function(newVal, oldVal) {
            this.serial_number = newVal.serial_number;
            this.id = newVal.id;
        }
    },
    template:
        '<div>' +
        '<input type="text" placeholder="Ломай меня полностью" v-model="serial_number" />' +
        '<input type="button" value="Save" @click="save" />' +
        '</div>',
    methods: {
        save: function() {
            var phototech = { serial_number: this.serial_number };

            if (this.id) {
                phototechApi.update({id: this.id}, phototech).then(result =>
                    result.json().then(data => {
                        var index = getIndex(data.id);
                        this.phototechs.splice(index, 1, data);
                        this.serial_number = ''
                        this.id = ''
                    })
                )
            } else {
                phototechApi.save({}, phototech).then(result =>
                    result.json().then(data => {
                        this.phototechs.push(data);
                        this.serial_number = ''
                    })
                )
            }
        }
    }
});

Vue.component('phototech-row', {
    props: ['phototech', 'editMethod', 'phototechs'],
    template: '<div>' +
        '<i>({{ phototech.id }})</i> {{ phototech.serial_number }}' +
        '<span style="position: absolute; right: 0">' +
        '<input type="button" value="Edit" @click="edit" />' +
        '<input type="button" value="X" @click="del" />' +
        '</span>' +
        '</div>',
    methods: {
        edit: function() {
            this.editMethod(this.phototech);
        },
        del: function() {
            phototechApi.remove({id: this.phototech.id}).then(result => {
                if (result.ok) {
                    this.phototechs.splice(this.phototechs.indexOf(this.phototech), 1)
                }
            })
        }
    }
});

Vue.component('phototechs-list', {
    props: ['phototechs'],
    data: function() {
        return {
            phototech: null
        }
    },
    template:
        '<div style="position: relative; width: 300px;">' +
        '<phototech-form :phototechs="phototechs" :phototechAttr="phototech" />' +
        '<phototech-row v-for="phototech in phototechs" :key="phototech.id" :phototech="phototech" ' +
        ':editMethod="editMethod" :phototechs="phototechs" />' +
        '</div>',
    created: function() {
        phototechApi.get().then(result =>
            result.json().then(data =>
                data.forEach(phototech => this.phototechs.push(phototech))
            )
        )
    },
    methods: {
        editMethod: function(phototech) {
            this.phototech = phototech;
        }
    }
});

var app = new Vue({
    el: '#app',
    template: '<phototechs-list :phototechs="phototechs" />',
    data: {
        phototechs: []
    }
});