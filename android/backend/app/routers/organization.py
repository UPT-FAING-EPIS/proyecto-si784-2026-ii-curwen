from fastapi import APIRouter
router=APIRouter(prefix="/organizacion",tags=["organizacion"])
@router.get("/sin-clasificar")
async def unclassified(): return [{"id":1,"nombre":"captura_2026-09-30.png","estado":"SIN_CLASIFICAR"},{"id":2,"nombre":"apunte_sin_titulo.pdf","estado":"SIN_CLASIFICAR"}]
@router.get("/papelera")
async def trash(): return []
@router.get("/anotaciones")
async def notes(): return [{"id":1,"contenido":"Revisar material de la semana 6","curso":"Ingeniería de Software"}]
