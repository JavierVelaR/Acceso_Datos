CREATE TYPE empleado1 AS OBJECT (
  id_empleado NUMBER(4),
  nombre VARCHAR2(30),
  apellido VARCHAR2(40),
  MAP MEMBER FUNCTION get_id_empleado RETURN NUMBER
);
/

-- Crear el cuerpo del tipo de objeto empleado 2
CREATE TYPE BODY empleado1 AS
  MAP MEMBER FUNCTION get_id_empleado RETURN NUMBER IS
  BEGIN
    RETURN id_empleado;
  END;
END;
/

CREATE OR REPLACE PROCEDURE ejecutar_bloque_plsql(p_output OUT VARCHAR2) AS
  emp empleado1;
BEGIN
  -- Inicializar la variable empleado
  emp := empleado1(1, 'Alberto', 'Ruiz');


  -- Acceder a los atributos del objeto
  p_output := 'ID Empleado: ' || emp.id_empleado || CHR(10) ||
              'Nombre: ' || emp.nombre || CHR(10) ||
              'Apellido: ' || emp.apellido || CHR(10) ||
              'ID Empleado desde la función: ' || emp.get_id_empleado();
END;
/

-- EJERCICIO 2---------------crear el tipo de objeto empleado3
CREATE TYPE empleado3 AS OBJECT (
  id_empleado NUMBER(4),
  nombre VARCHAR2(30),
  apellido VARCHAR2(40),
  MAP MEMBER FUNCTION get_id_empleado RETURN NUMBER
);
/

-- Crear el cuerpo del tipo de objeto empleado 3
CREATE TYPE BODY empleado3 AS
  MAP MEMBER FUNCTION get_id_empleado RETURN NUMBER IS
  BEGIN
    RETURN id_empleado;
  END;
END;
/

CREATE OR REPLACE PROCEDURE ejecutar_ejercicio2 (p_output OUT VARCHAR2) AS
  emp empleado3;
BEGIN
  -- Inicializar la variable empleado
  emp := empleado3(3, 'Irene', 'Guerrera');


  -- Acceder a los atributos del objeto
  p_output := 'ID Empleado: ' || emp.id_empleado || CHR(10) ||
              'Nombre: ' || emp.nombre || CHR(10) ||
              'Apellido: ' || emp.apellido || CHR(10) ||
              'ID Empleado desde la función: ' || emp.get_id_empleado();
END;
/

-- EJERCICIO 3---------------crear el tipo de objeto alumno
CREATE TYPE alumno AS OBJECT (
  id_alumno NUMBER(4),
  nombre VARCHAR2(30),
  apellido VARCHAR2(40),
  MAP MEMBER FUNCTION get_id_alumno RETURN NUMBER
);
/

-- Crear el cuerpo del tipo de objeto alumno
CREATE TYPE BODY alumno AS
  MAP MEMBER FUNCTION get_id_alumno RETURN NUMBER IS
  BEGIN
    RETURN id_alumno;
  END;
END;
/

CREATE OR REPLACE PROCEDURE ejecutar_ejercicio3 (p_output OUT VARCHAR2) AS
  al alumno;
BEGIN
  -- Inicializar la variable empleado
  al := alumno(4, 'Daniel', 'Vargas');


  -- Acceder a los atributos del objeto
  p_output := 'ID Alumno: ' || al.id_alumno || CHR(10) ||
              'Nombre: ' || al.nombre || CHR(10) ||
              'Apellido: ' || al.apellido || CHR(10) ||
              'ID Alumno desde la función: ' || al.get_id_alumno();
END;
/

-- Reto
CREATE TYPE profesor AS OBJECT (
  id_profesor NUMBER(4),
  apodo VARCHAR(20),
  nombre VARCHAR2(30),
  apellido VARCHAR2(40),
  vivienda VARCHAR(20),
  MAP MEMBER FUNCTION get_id_profesor RETURN NUMBER
);
/

-- Crear el cuerpo del tipo de objeto alumno
CREATE TYPE BODY profesor AS
  MAP MEMBER FUNCTION get_id_profesor RETURN NUMBER IS
  BEGIN
    RETURN id_profesor;
  END;
END;
/

CREATE OR REPLACE PROCEDURE ejecutar_reto (p_output OUT VARCHAR2) AS
  pr profesor;
BEGIN
  -- Inicializar la variable empleado
  pr := profesor(1, 'superalbertron', 'Alberto', 'Ruíz', 'Al lado del Vialia');


  -- Acceder a los atributos del objeto
  p_output := 'ID Profesor: ' || pr.id_profesor || CHR(10) ||
                'Apodo: ' || pr.apodo || CHR(10) ||
              'Nombre: ' || pr.nombre || CHR(10) ||
              'Apellido: ' || pr.apellido || CHR(10) ||
              'Vivienda: ' || pr.vivienda || CHR(10) ||
              'ID Profesor desde la función: ' || pr.get_id_profesor();
END;
/
