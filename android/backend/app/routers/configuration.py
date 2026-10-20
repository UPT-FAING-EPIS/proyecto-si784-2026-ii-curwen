from fastapi import APIRouter
router=APIRouter(prefix="/configuracion",tags=["configuracion"])
@router.get("/perfil")
async def profile(): return {"nombre":"Antony Solorzano","correo":"antony@upt.edu.pe","carrera":"Ingeniería de Sistemas","ciclo":6}
@router.get("/horario")
async def schedule(): return {"bloques":[{"dia":"LUNES","inicio":"08:00","fin":"10:00","tipo":"CLASE"},{"dia":"MARTES","inicio":"16:00","fin":"18:00","tipo":"CLASE"}]}
@router.get("/preferencias")
async def preferences(): return {"duracion_bloque_min":45,"franja_preferida":"NOCHE","dias_anticipacion":3,"max_horas_diarias":3}
