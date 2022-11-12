import React, { useState } from 'react'
import PositionService from '../services/PositionService';

function CalculDistanceForm({loading,positions}) {
    const [distance, setDistance] = useState();

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
  )
}

export default CalculDistanceForm