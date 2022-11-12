class PositionService {

    getAll(){
        return fetch('/api/positions');
    }

    delete(id){
        return fetch(`/api/positions/${id}`,
        {
            method: 'DELETE'
        });
    }

    create(longitude, latitude){
        return fetch('/api/positions',
        {
            method: 'POST',
            body: JSON.stringify({longitude, latitude}),
            headers : {
                'Content-Type': 'application/json'
            }
        });
    }

    getDistance(idFrom, idTo){
        return fetch(`/api/positions/${idFrom}/distance/${idTo}`,
        {
            method: 'POST'
        })
    }
}

export default new PositionService();