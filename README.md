
### El grupo esta formado por:
### -Agustin Bruno
### -Guillermo Bordoli
### -Santino Fierro
# 🧑‍💻 PARTE 1 – Acciones en GitHub

### ✅ 1. Aceptar la invitación
1. Iniciá sesión en [GitHub.com](https://github.com).
2. En la parte superior o en tus notificaciones, aceptá la invitación que te envió el dueño del repositorio (agustinbruno07).
3. ).

---

### 🔁 2. Crear un Pull Request (después de subir tu rama)
1. Luego de hacer `push` de tu rama desde Git Bash (ver Parte 2), entrá al repositorio en GitHub.
2. GitHub te va a sugerir un botón que dice **“Compare & pull request”**.
3. Tocá ese botón.
4. Escribí un título claro (por ejemplo, "Agregué menú de opciones").
5. Podés agregar una descripción si querés.
6. Hacé clic en **“Create pull request”**.
7. El dueño del repositorio va a revisar y aceptar (o pedir cambios).

---

# 💻 PARTE 2 – Acciones en Git Bash

### 📥 1. Clonar el repositorio

```bash
git clone https://github.com/guillebordoli/calculadora.git
cd calculadora
```


### 🌿 2. Crear tu propia rama de trabajo

```bash
git checkout -b (aca pones tu nombre)
```
<details> <summary> 📌 Ejemplo: </summary>
  
```bash
git checkout -b juan
```
</details>


  
### 💾 3. Hacer cambios y guardarlos
```bash
git add .
git commit -m "Descripción de lo que hiciste"
```



### 🚀 4. Subir tu rama al repositorio (GitHub)
```bash
git push origin tu-nombre
```
**Esto `sube` tu rama a GitHub y te habilita para hacer el pull request desde la web.**





# 🔄 PARTE 3 – Mantener tu rama actualizada con master
**Si otro compañero subió cambios al repositorio principal `master`, tenés que traer esos cambios a tu rama para no quedarte atrás ni generar conflictos.**
### 🧩 Pasos para actualizar tu rama:
```bash
git checkout master
git pull origin master
git checkout (aca va tu rama, o sea tu nombre)
git merge master
```
<details>
<summary> 📌 Explicación</summary>

  ```bash
  git checkout master → Vas a la rama principal.
  git pull origin master → Bajás los últimos cambios del repositorio.
  git checkout tu-nombre → Volvés a tu rama.
  git merge master → Unís los cambios de master con los tuyos.
  ```

</details>





# 🧹 PARTE 4 – Limpiar ramas viejas (opcional)

### 1. Borrar la rama local
```bash
git branch -d (aca va tu rama, o sea tu nombre)
```
### 2. Borrar la rama del repositorio remoto (GitHub)
```bash
git push origin --delete tu-nombre
```
