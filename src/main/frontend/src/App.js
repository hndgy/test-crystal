import { useEffect, useState } from "react";
import PositionService from "./services/PositionService";

function App() {
  const [positions, setPositions] = useState([]);
  const [loading, setLoading] = useState(true);

  const [distance, setDistance] = useState();
  useEffect(() => {
    PositionService.getAll().then(res => res.json())
      .then(data => {
        setPositions(data);
        setLoading(false);
      })
  },[])

  const handleDelete = (id) => {
    PositionService.delete(id).then(rep => {
      setLoading(true);

      if(rep.status === 204){//No content status
        var copy = [...positions];
        copy = copy.filter(x => x.id !== id);
        setPositions(copy);
      }else{
        alert("Impossible de supprimer la position")
      }

      setLoading(false);
    })
  }

  const handleCreate = (e) =>{
    e.preventDefault();
    const formData = new FormData(e.currentTarget);
    PositionService.create(
      formData.get("longitude"),
      formData.get("latitude")
    ).then(resp => resp.json())
      .then(
        data => {
          setLoading(true);
          if(data.id){
            var copy = [...positions,data]
            setPositions(copy)
          }
          setLoading(false);
        }
      )
  }

  const getDistance = (e) => {
    e.preventDefault();
    const formData = new FormData(e.currentTarget);

    const idFrom = formData.get("idFrom");
    const idTo = formData.get("idTo");
    if(idFrom && idTo){
      PositionService.getDistance(idFrom, idTo).then(resp => resp.json())
              .then(data => setDistance(data))
    }
    else {
      setDistance(null)
    }
  }

  return (
    <div>
      <form name="createForm" onSubmit={handleCreate}>
        <label>
          Longitude :
        <input name="longitude"/>
        </label>
        <label>
          Latitude :
        <input name="latitude" />
        </label>
        <button type="submit">Créer</button>
      </form>
      {
      (!loading &&
          positions.map(pos =>
            <p key={pos.id}>[id : {pos.id}] = longitude : <b>{pos.longitude}</b>, latitude : <b>{pos.latitude}</b> 
                <button onClick={() => handleDelete(pos.id)}>supprimer</button>
            </p>
          )
        )|| <p>Chargement...</p>
        }

      <h2>Calcul distance</h2>
      <form name="calculForm" onSubmit={getDistance}>

      <span>De </span>
      <select disabled={loading} name="idFrom">
        <option value={null}>Veuillez choisir une position</option>
        {positions.map(pos => 
          <option value={pos.id} key={pos.id} > 
                  {pos.id} long:{pos.longitude},lat:{pos.latitude}
          </option>
          )
        }
      </select> 
      <span> à </span> 
      <select disabled={loading} name="idTo">
        <option value={null}>Veuillez choisir une position</option>

        {positions.map(pos => 
            <option value={pos.id} key={pos.id} > 
                      {pos.id} long:{pos.longitude},lat:{pos.latitude}
                    </option>
          )
        }
      </select>
      <div>
        <button>Calculer</button>
      </div>
      </form>

      {distance &&
        <p>Distance = <b>{distance}</b> km {distance <= 10. ? "(<10km)" : ""}</p>
      }
    </div>
  );
}

export default App;
