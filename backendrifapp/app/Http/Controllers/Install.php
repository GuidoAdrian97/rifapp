<?php

namespace App\Http\Controllers;

use App\Http\Controllers\Controller;
use Illuminate\Http\Request;
use Laravel\Socialite\Facades\Socialite;
use Illuminate\Support\Facades\Auth;
use App\Models\OauthAccessToken;
use Illuminate\Support\Carbon;
use App\Models\User;
use Illuminate\Support\Facades\Http;
use Illuminate\Support\Facades\Hash;
use App\Models\Rifas\MetodoSorteo;
use App\Models\Rifas\Prizecate;
use App\Models\Billetera\Wallet;
use App\Models\Billetera\Tiposcuenta;
use App\Models\Billetera\Cuenta;
use App\Models\Referido\ReferralCode;
use App\Models\Referido\level_user_referrals;
use App\Models\Referido\Principalreferral;
use Google_Client;

class Install extends Controller
{
    public function Install(Request $request){
        // Tiposcuenta
        Tiposcuenta::insert([
            ['type_cuenta' => 'recargas', 'descripcion_type_cuenta' => 'Cuenta de recargas'],
            ['type_cuenta' => 'retiros', 'descripcion_type_cuenta' => 'Cuenta de retiros'],
            ['type_cuenta' => 'ganancias', 'descripcion_type_cuenta' => 'Cuenta de ganancias'],
            ['type_cuenta' => 'master_fondos', 'descripcion_type_cuenta' => 'Cuenta de fondos donde llegara todo el dinero para recargar mas cuentas '],
            ['type_cuenta' => 'master_ganancias', 'descripcion_type_cuenta' => 'Cuenta de ganancias master aqui se encontrara la ganancia del %5 de cada recarga'],
        ]);
        MetodoSorteo::insert([
        ['nombre_sorteo' => 'Loteria Nacional','estado_tipo_sorteo' => 1, 'descripcion_sorteo' => 'Este sorteo solo se puede utilizar para sorteos que se realicen los dias lunes miercoles y viernes, y los responsables de los resultados de los numeros es la Loteria Nacional'],
        ['nombre_sorteo' => 'Rifalo Ya','estado_tipo_sorteo' => 1, 'descripcion_sorteo' => 'Este sorteo se realiza mediante un porceso logico, matematico seguro que genera numeros aleatorios donde NO se puede predecir los resultados.'],
        ]);

        Prizecate::insert([
            ['name_prizecates' => 'Celular', 'slug_prizecates' => 'celular', 'description_prizecates' => 'Categoria dirigida a articulos telefonicos inalambricos que pueden tener o no chip y que se consideren telefonos mobiles'],
            ['name_prizecates' => 'Laptop', 'slug_prizecates' => 'laptop', 'description_prizecates' => 'Categoria dirigida a computadoras portatiles con capacidad de bateria integrada y que tengan un sistema operativo instalado'],
            ['name_prizecates' => 'Televisor', 'slug_prizecates' => 'televisor', 'description_prizecates' => 'Categoria dirigida a dispositivos con capacidad de recibir senales de transmision de video y audio para ser visualizados en pantalla'],
            ['name_prizecates' => 'Tablet', 'slug_prizecates' => 'tablet', 'description_prizecates' => 'Categoria dirigida a dispositivos portatiles con pantalla tactil, generalmente de mayor tamano que un telefono movil, y con capacidad de ejecutar aplicaciones'],
            ['name_prizecates' => 'Cámara', 'slug_prizecates' => 'camara', 'description_prizecates' => 'Categoria dirigida a dispositivos para capturar fotografias o videos, generalmente con lentes intercambiables'],
            ['name_prizecates' => 'Consola de videojuegos', 'slug_prizecates' => 'consola-videojuegos', 'description_prizecates' => 'Categoria dirigida a dispositivos para jugar videojuegos, que pueden ser conectados a una pantalla de television'],
            ['name_prizecates' => 'Smartwatch', 'slug_prizecates' => 'smartwatch', 'description_prizecates' => 'Categoria dirigida a relojes inteligentes con funciones avanzadas, como monitoreo de la salud y notificaciones de smartphone'],
            ['name_prizecates' => 'Impresora', 'slug_prizecates' => 'impresora', 'description_prizecates' => 'Categoria dirigida a dispositivos para imprimir documentos o imagenes en papel'],
            ['name_prizecates' => 'Altavoces inteligentes', 'slug_prizecates' => 'altavoces-inteligentes', 'description_prizecates' => 'Categoria dirigida a dispositivos con capacidad de reproduccion de audio y control de otros dispositivos mediante comandos de voz'],
            ['name_prizecates' => 'Auriculares', 'slug_prizecates' => 'auriculares', 'description_prizecates' => 'Categoria dirigida a dispositivos para escuchar audio personalmente, que pueden ser con cable o inalambricos'],
            ['name_prizecates' => 'Drone', 'slug_prizecates' => 'drone', 'description_prizecates' => 'Categoria dirigida a vehiculos aereos no tripulados, controlados a distancia y utilizados para diversos fines'],
            ['name_prizecates' => 'Robot aspirador', 'slug_prizecates' => 'robot-aspirador', 'description_prizecates' => 'Categoria dirigida a dispositivos autonomos utilizados para limpiar suelos, que utilizan tecnologia de sensores y navegacion'],
            ['name_prizecates' => 'Cafetera', 'slug_prizecates' => 'cafetera', 'description_prizecates' => 'Categoria dirigida a dispositivos para preparar cafe, que pueden ser automaticos o manuales'],
            ['name_prizecates' => 'Bicicleta eléctrica', 'slug_prizecates' => 'bicicleta-electrica', 'description_prizecates' => 'Categoria dirigida a bicicletas con motor electrico, que asisten al pedaleo del usuario'],
            ['name_prizecates' => 'Aspiradora', 'slug_prizecates' => 'aspiradora', 'description_prizecates' => 'Categoria dirigida a dispositivos para limpiar suelos, que utilizan succion para recoger polvo y particulas'],
            ['name_prizecates' => 'Horno microondas', 'slug_prizecates' => 'horno-microondas', 'description_prizecates' => 'Categoria dirigida a dispositivos para cocinar o calentar alimentos mediante microondas'],
            ['name_prizecates' => 'Refrigerador', 'slug_prizecates' => 'refrigerador', 'description_prizecates' => 'Categoria dirigida a dispositivos para mantener alimentos y bebidas frescos mediante enfriamiento'],
            ['name_prizecates' => 'Reloj inteligente', 'slug_prizecates' => 'reloj-inteligente', 'description_prizecates' => 'Categoria dirigida a relojes con funcionalidades avanzadas, como notificaciones y seguimiento de actividad fisica'],
            ['name_prizecates' => 'Micrófono', 'slug_prizecates' => 'microfono', 'description_prizecates' => 'Categoria dirigida a dispositivos para capturar audio, que pueden ser utilizados en grabaciones o transmisiones'],
            ['name_prizecates' => 'Batería externa', 'slug_prizecates' => 'bateria-externa', 'description_prizecates' => 'Categoria dirigida a dispositivos portatiles para cargar otros dispositivos, como telefonos moviles y tabletas'],
            ['name_prizecates' => 'Teclado mecánico', 'slug_prizecates' => 'teclado-mecanico', 'description_prizecates' => 'Categoria dirigida a teclados para computadora con interruptores mecanicos, que ofrecen una experiencia de escritura mejorada'],
            ['name_prizecates' => 'Mochila para portátil', 'slug_prizecates' => 'mochila-portatil', 'description_prizecates' => 'Categoria dirigida a mochilas diseñadas para transportar computadoras portátiles de forma segura y cómoda'],
            ['name_prizecates' => 'Monitor de computadora', 'slug_prizecates' => 'monitor-computadora', 'description_prizecates' => 'Categoria dirigida a dispositivos de salida visual para computadoras, que muestran imágenes y texto generados por el sistema'],
            ['name_prizecates' => 'Cargador inalámbrico', 'slug_prizecates' => 'cargador-inalambrico', 'description_prizecates' => 'Categoria dirigida a dispositivos para cargar smartphones y otros dispositivos compatibles con tecnología de carga inalámbrica'],
            ['name_prizecates' => 'Estación de trabajo', 'slug_prizecates' => 'estacion-trabajo', 'description_prizecates' => 'Categoria dirigida a escritorios o mesas especialmente diseñadas para ser utilizadas como centros de trabajo, con espacio para computadora, monitores y otros dispositivos'],
            ['name_prizecates' => 'Cámara deportiva', 'slug_prizecates' => 'camara-deportiva', 'description_prizecates' => 'Categoria dirigida a cámaras diseñadas para ser utilizadas en actividades deportivas y al aire libre, con resistencia a golpes y agua'],
            ['name_prizecates' => 'Silla de oficina', 'slug_prizecates' => 'silla-oficina', 'description_prizecates' => 'Categoria dirigida a sillas ergonómicas diseñadas para su uso en entornos de oficina, que proporcionan comodidad y soporte para largas horas de trabajo'],
            ['name_prizecates' => 'Tarjeta de regalo', 'slug_prizecates' => 'tarjeta-regalo', 'description_prizecates' => 'Categoria dirigida a tarjetas prepagadas que pueden ser utilizadas como forma de pago en tiendas y establecimientos participantes'],
            ['name_prizecates' => 'Estuche para teléfono', 'slug_prizecates' => 'estuche-telefono', 'description_prizecates' => 'Categoria dirigida a fundas o estuches diseñados para proteger teléfonos móviles de golpes, caídas y arañazos'],
            ['name_prizecates' => 'Memoria USB', 'slug_prizecates' => 'memoria-usb', 'description_prizecates' => 'Categoria dirigida a dispositivos de almacenamiento portátiles, que utilizan tecnología de memoria flash para guardar y transferir datos'],
            ['name_prizecates' => 'Funda para portátil', 'slug_prizecates' => 'funda-portatil', 'description_prizecates' => 'Categoria dirigida a fundas o estuches diseñados para proteger computadoras portátiles de daños y arañazos durante el transporte'],
            ['name_prizecates' => 'Teclado inalámbrico', 'slug_prizecates' => 'teclado-inalambrico', 'description_prizecates' => 'Categoria dirigida a teclados para computadora que se conectan de forma inalámbrica a través de tecnologías como Bluetooth o RF'],
            ['name_prizecates' => 'Almohadilla de carga', 'slug_prizecates' => 'almohadilla-carga', 'description_prizecates' => 'Categoria dirigida a bases de carga inalámbrica para smartphones y otros dispositivos compatibles, que permiten la recarga sin necesidad de cables'],
            ['name_prizecates' => 'Cable USB', 'slug_prizecates' => 'cable-usb', 'description_prizecates' => 'Categoria dirigida a cables de conexión para dispositivos electrónicos, que utilizan el estándar USB para transferir datos y energía'],
            ['name_prizecates' => 'Kit de limpieza para computadora', 'slug_prizecates' => 'kit-limpieza-computadora', 'description_prizecates' => 'Categoria dirigida a conjuntos de herramientas y productos diseñados para limpiar y mantener computadoras y dispositivos electrónicos'],
            ['name_prizecates' => 'Organizador de cables', 'slug_prizecates' => 'organizador-cables', 'description_prizecates' => 'Categoria dirigida a accesorios para organizar y gestionar cables y alambres de computadoras y dispositivos electrónicos'],
            ['name_prizecates' => 'Base de refrigeración para portátil', 'slug_prizecates' => 'base-refrigeracion-portatil', 'description_prizecates' => 'Categoria dirigida a dispositivos con ventiladores integrados para enfriar computadoras portátiles y prevenir el sobrecalentamiento'],
            ['name_prizecates' => 'Funda para tableta', 'slug_prizecates' => 'funda-tableta', 'description_prizecates' => 'Categoria dirigida a fundas o estuches diseñados para proteger tablets y dispositivos similares de daños y arañazos'],
            ['name_prizecates' => 'Cámara de seguridad', 'slug_prizecates' => 'camara-seguridad', 'description_prizecates' => 'Categoria dirigida a cámaras utilizadas para la vigilancia y seguridad de hogares y negocios, con capacidad de grabación y monitoreo remoto'],
            ['name_prizecates' => 'Robot de cocina', 'slug_prizecates' => 'robot-cocina', 'description_prizecates' => 'Categoria dirigida a dispositivos automatizados para preparar alimentos, que pueden realizar diversas tareas culinarias con mínima intervención humana'],
            ['name_prizecates' => 'Sistema de sonido envolvente', 'slug_prizecates' => 'sonido-envolvente', 'description_prizecates' => 'Categoria dirigida a sistemas de audio que crean un ambiente envolvente al reproducir sonido desde múltiples altavoces ubicados alrededor del área de escucha'],
            ['name_prizecates' => 'Máquina de café espresso', 'slug_prizecates' => 'cafe-espresso', 'description_prizecates' => 'Categoria dirigida a máquinas especializadas en la preparación de café espresso, que utilizan presión para extraer el sabor de los granos de café molido'],
            ['name_prizecates' => 'Smart TV', 'slug_prizecates' => 'smart-tv', 'description_prizecates' => 'Categoria dirigida a televisores inteligentes con capacidad de conexión a Internet y funciones avanzadas, como aplicaciones integradas y control por voz'],
            ['name_prizecates' => 'Kit de herramientas', 'slug_prizecates' => 'kit-herramientas', 'description_prizecates' => 'Categoria dirigida a conjuntos de herramientas manuales o eléctricas para realizar reparaciones y proyectos de bricolaje en el hogar o el taller'],
            ['name_prizecates' => 'Lámpara inteligente', 'slug_prizecates' => 'lampara-inteligente', 'description_prizecates' => 'Categoria dirigida a lámparas con capacidad de control remoto y ajuste de intensidad y color de la luz a través de aplicaciones móviles o comandos de voz'],
            ['name_prizecates' => 'Cámara de acción', 'slug_prizecates' => 'camara-accion', 'description_prizecates' => 'Categoria dirigida a cámaras pequeñas y resistentes diseñadas para grabar videos en situaciones extremas y actividades deportivas'],
            ['name_prizecates' => 'Kit de accesorios para teléfono', 'slug_prizecates' => 'kit-accesorios-telefono', 'description_prizecates' => 'Categoria dirigida a conjuntos de accesorios útiles para teléfonos móviles, como fundas, protectores de pantalla, cargadores y auriculares'],
            ['name_prizecates' => 'Sistema de altavoces Bluetooth', 'slug_prizecates' => 'altavoces-bluetooth', 'description_prizecates' => 'Categoria dirigida a conjuntos de altavoces inalámbricos que se conectan a dispositivos mediante tecnología Bluetooth para reproducir audio de forma inalámbrica'],
            ['name_prizecates' => 'Estación de carga USB', 'slug_prizecates' => 'estacion-carga-usb', 'description_prizecates' => 'Categoria dirigida a estaciones de carga múltiple para dispositivos USB, que permiten cargar varios dispositivos simultáneamente desde una sola toma de corriente'],
            ['name_prizecates' => 'Máquina de afeitar eléctrica', 'slug_prizecates' => 'afeitadora-electrica', 'description_prizecates' => 'Categoria dirigida a dispositivos para el cuidado personal masculino, que utilizan cuchillas giratorias o láminas para afeitar el vello facial'],
            ['name_prizecates' => 'Kit de herramientas para reparación de teléfonos', 'slug_prizecates' => 'kit-herramientas-reparacion-telefonos', 'description_prizecates' => 'Categoria dirigida a conjuntos de herramientas especializadas para la reparación y mantenimiento de teléfonos móviles y tabletas'],
            ['name_prizecates' => 'Lentes de cámara para teléfono', 'slug_prizecates' => 'lentes-camara-telefono', 'description_prizecates' => 'Categoria dirigida a lentes ópticas que se acoplan a la cámara de un teléfono móvil para mejorar la calidad y versatilidad de las fotografías'],
            ['name_prizecates' => 'Estación meteorológica doméstica', 'slug_prizecates' => 'estacion-meteorologica', 'description_prizecates' => 'Categoria dirigida a dispositivos para medir y monitorear condiciones meteorológicas en el hogar, como temperatura, humedad y presión atmosférica'],
            ['name_prizecates' => 'Kit de herramientas para jardinería', 'slug_prizecates' => 'kit-herramientas-jardineria', 'description_prizecates' => 'Categoria dirigida a conjuntos de herramientas manuales para el cuidado y mantenimiento de jardines y áreas verdes'],
            ['name_prizecates' => 'Kit de seguridad para el hogar', 'slug_prizecates' => 'kit-seguridad-hogar', 'description_prizecates' => 'Categoria dirigida a conjuntos de dispositivos para la protección y seguridad del hogar, como cámaras de vigilancia, sensores de movimiento y cerraduras inteligentes'],
            ['name_prizecates' => 'Suscripción a servicio de streaming', 'slug_prizecates' => 'suscripcion-streaming', 'description_prizecates' => 'Categoria dirigida a suscripciones pagas a servicios de transmisión de contenido multimedia en línea, como películas, series de televisión y música'],
            ['name_prizecates' => 'Robot limpiador de piscinas', 'slug_prizecates' => 'robot-limpiador-piscinas', 'description_prizecates' => 'Categoria dirigida a dispositivos automatizados para limpiar piscinas, que utilizan cepillos y aspiradoras integradas para eliminar suciedad y escombros'],
            ['name_prizecates' => 'Estación de trabajo para computadora portátil', 'slug_prizecates' => 'estacion-trabajo-portatil', 'description_prizecates' => 'Categoria dirigida a soportes y accesorios para crear un espacio de trabajo ergonómico con una computadora portátil, incluyendo elevadores, bases y ventiladores de enfriamiento'],
            ['name_prizecates' => 'Lámpara de escritorio', 'slug_prizecates' => 'lampara-escritorio', 'description_prizecates' => 'Categoria dirigida a lámparas diseñadas específicamente para su uso en escritorios y espacios de trabajo'],
            ['name_prizecates' => 'Auriculares inalámbricos', 'slug_prizecates' => 'auriculares-inalambricos', 'description_prizecates' => 'Categoria dirigida a auriculares que se conectan de forma inalámbrica a dispositivos mediante tecnologías como Bluetooth'],
            ['name_prizecates' => 'Impresora multifuncional', 'slug_prizecates' => 'impresora-multifuncional', 'description_prizecates' => 'Categoria dirigida a impresoras que ofrecen funciones adicionales además de la impresión, como escaneo, copiado y fax'],
            ['name_prizecates' => 'Juego de herramientas para bricolaje', 'slug_prizecates' => 'herramientas-bricolaje', 'description_prizecates' => 'Categoria dirigida a conjuntos completos de herramientas manuales y eléctricas para proyectos de bricolaje y reparaciones domésticas'],
            ['name_prizecates' => 'Altavoz inteligente', 'slug_prizecates' => 'altavoz-inteligente', 'description_prizecates' => 'Categoria dirigida a altavoces equipados con asistentes de voz inteligentes, que pueden reproducir música, responder preguntas y controlar dispositivos del hogar'],
            ['name_prizecates' => 'Cámara instantánea', 'slug_prizecates' => 'camara-instantanea', 'description_prizecates' => 'Categoria dirigida a cámaras que imprimen fotografías instantáneas en papel fotográfico, proporcionando una experiencia analógica en la era digital'],
            ['name_prizecates' => 'Telescopio astronómico', 'slug_prizecates' => 'telescopio-astronomico', 'description_prizecates' => 'Categoria dirigida a dispositivos ópticos para observar el cielo y los astros, que permiten explorar el universo desde la comodidad del hogar'],
            ['name_prizecates' => 'Kit de construcción de robots', 'slug_prizecates' => 'kit-construccion-robots', 'description_prizecates' => 'Categoria dirigida a conjuntos de piezas y componentes para construir robots y máquinas controladas por programación'],
            ['name_prizecates' => 'Sistema de seguridad para automóvil', 'slug_prizecates' => 'seguridad-automovil', 'description_prizecates' => 'Categoria dirigida a dispositivos y sistemas diseñados para proteger vehículos de robos y daños, como alarmas, cámaras y bloqueos'],
            ['name_prizecates' => 'Kit de herramientas para reparación de bicicletas', 'slug_prizecates' => 'kit-herramientas-bicicletas', 'description_prizecates' => 'Categoria dirigida a conjuntos de herramientas especializadas para el mantenimiento y reparación de bicicletas y ciclomotores'],
            ['name_prizecates' => 'Sistema de filtración de agua', 'slug_prizecates' => 'filtracion-agua', 'description_prizecates' => 'Categoria dirigida a dispositivos y filtros para purificar y mejorar la calidad del agua potable en el hogar'],
            ['name_prizecates' => 'Bolso de viaje', 'slug_prizecates' => 'bolso-viaje', 'description_prizecates' => 'Categoria dirigida a bolsos y maletas diseñados para transportar ropa y pertenencias durante viajes y desplazamientos'],
            ['name_prizecates' => 'Reloj inteligente deportivo', 'slug_prizecates' => 'reloj-inteligente-deportivo', 'description_prizecates' => 'Categoria dirigida a relojes diseñados para deportistas y entusiastas del fitness, que ofrecen funciones avanzadas como seguimiento de actividad, GPS y resistencia al agua'],
            ['name_prizecates' => 'Sistema de iluminación inteligente', 'slug_prizecates' => 'iluminacion-inteligente', 'description_prizecates' => 'Categoria dirigida a sistemas de iluminación para el hogar que pueden controlarse de forma remota y programarse para ajustar el color y la intensidad de la luz'],
            ['name_prizecates' => 'Tabla de surf', 'slug_prizecates' => 'tabla-surf', 'description_prizecates' => 'Categoria dirigida a tablas diseñadas para la práctica del surf en diferentes condiciones de oleaje y estilo de surfista'],
            ['name_prizecates' => 'Kit de herramientas para modelismo', 'slug_prizecates' => 'kit-herramientas-modelismo', 'description_prizecates' => 'Categoria dirigida a conjuntos de herramientas especializadas para la construcción y mantenimiento de maquetas y modelos a escala'],
            ['name_prizecates' => 'Kit de accesorios para fotografía', 'slug_prizecates' => 'kit-accesorios-fotografia', 'description_prizecates' => 'Categoria dirigida a conjuntos de accesorios útiles para fotógrafos aficionados y profesionales, como trípodes, filtros y difusores de luz'],
            ['name_prizecates' => 'Suscripción a revista digital', 'slug_prizecates' => 'suscripcion-revista-digital', 'description_prizecates' => 'Categoria dirigida a suscripciones a publicaciones periódicas en formato digital, que ofrecen contenido exclusivo y acceso a ediciones anteriores'],
            ['name_prizecates' => 'Servicio de limpieza', 'slug_prizecates' => 'servicio-limpieza', 'description_prizecates' => 'Servicio de limpieza profesional para hogares, oficinas y espacios comerciales'],
            ['name_prizecates' => 'Servicio de catering', 'slug_prizecates' => 'servicio-catering', 'description_prizecates' => 'Servicio de preparación y entrega de alimentos para eventos y reuniones'],
            ['name_prizecates' => 'Servicio de reparación de automóviles', 'slug_prizecates' => 'servicio-reparacion-automoviles', 'description_prizecates' => 'Reparación y mantenimiento de vehículos automotores'],
            ['name_prizecates' => 'Servicio de mudanzas', 'slug_prizecates' => 'servicio-mudanzas', 'description_prizecates' => 'Servicio de traslado de mobiliario y enseres de un lugar a otro'],
            ['name_prizecates' => 'Servicio de jardinería', 'slug_prizecates' => 'servicio-jardineria', 'description_prizecates' => 'Mantenimiento y cuidado de jardines y áreas verdes'],
            ['name_prizecates' => 'Servicio de consultoría empresarial', 'slug_prizecates' => 'servicio-consultoria-empresarial', 'description_prizecates' => 'Asesoramiento estratégico y operativo para empresas'],
            ['name_prizecates' => 'Servicio de limpieza de alfombras', 'slug_prizecates' => 'servicio-limpieza-alfombras', 'description_prizecates' => 'Limpieza profesional de alfombras y tapetes'],
            ['name_prizecates' => 'Servicio de fontanería', 'slug_prizecates' => 'servicio-fontaneria', 'description_prizecates' => 'Reparación e instalación de sistemas de fontanería'],
            ['name_prizecates' => 'Servicio de cuidado de niños', 'slug_prizecates' => 'servicio-cuidado-ninos', 'description_prizecates' => 'Cuidado y atención de niños y niñas en ausencia de los padres'],
            ['name_prizecates' => 'Servicio de limpieza de ventanas', 'slug_prizecates' => 'servicio-limpieza-ventanas', 'description_prizecates' => 'Limpieza profesional de ventanas y cristales'],
            ['name_prizecates' => 'Servicio de diseño de interiores', 'slug_prizecates' => 'servicio-diseno-interiores', 'description_prizecates' => 'Diseño y decoración de interiores de viviendas y espacios comerciales'],
            ['name_prizecates' => 'Servicio de reparación de electrodomésticos', 'slug_prizecates' => 'servicio-reparacion-electrodomesticos', 'description_prizecates' => 'Reparación y mantenimiento de electrodomésticos del hogar'],
            ['name_prizecates' => 'Servicio de fotografía', 'slug_prizecates' => 'servicio-fotografia', 'description_prizecates' => 'Sesiones fotográficas para eventos, retratos, productos, entre otros'],
            ['name_prizecates' => 'Servicio de mantenimiento de piscinas', 'slug_prizecates' => 'servicio-mantenimiento-piscinas', 'description_prizecates' => 'Mantenimiento y limpieza de piscinas residenciales y comerciales'],
            ['name_prizecates' => 'Servicio de contabilidad', 'slug_prizecates' => 'servicio-contabilidad', 'description_prizecates' => 'Gestión contable y financiera para empresas y particulares'],
            ['name_prizecates' => 'Servicio de reparación de ordenadores', 'slug_prizecates' => 'servicio-reparacion-ordenadores', 'description_prizecates' => 'Reparación y mantenimiento de equipos informáticos y ordenadores'],
            ['name_prizecates' => 'Servicio de entrenamiento personal', 'slug_prizecates' => 'servicio-entrenamiento-personal', 'description_prizecates' => 'Entrenamiento físico personalizado y asesoramiento nutricional'],
            ['name_prizecates' => 'Servicio de lavandería', 'slug_prizecates' => 'servicio-lavanderia', 'description_prizecates' => 'Limpieza y planchado de prendas de vestir y textiles del hogar'],
            ['name_prizecates' => 'Servicio de reparación de aparatos electrónicos', 'slug_prizecates' => 'servicio-reparacion-aparatos-electronicos', 'description_prizecates' => 'Reparación de dispositivos electrónicos y aparatos domésticos'],
            ['name_prizecates' => 'Servicio de instalación de aire acondicionado', 'slug_prizecates' => 'servicio-instalacion-aire-acondicionado', 'description_prizecates' => 'Instalación y mantenimiento de sistemas de aire acondicionado'],
            ['name_prizecates' => 'Servicio de diseño de catálogos', 'slug_prizecates' => 'servicio-diseno-catalogos', 'description_prizecates' => 'Diseño de catálogos impresos y digitales para productos y servicios'],
            ['name_prizecates' => 'Corte y peinado de cabello', 'slug_prizecates' => 'corte-peinado-cabello', 'description_prizecates' => 'Servicio de corte de cabello y estilismo para hombres y mujeres'],
            ['name_prizecates' => 'Tratamientos faciales', 'slug_prizecates' => 'tratamientos-faciales', 'description_prizecates' => 'Limpiezas faciales, exfoliaciones, tratamientos antiarrugas, entre otros'],
            ['name_prizecates' => 'Maquillaje profesional', 'slug_prizecates' => 'maquillaje-profesional', 'description_prizecates' => 'Aplicación de maquillaje para eventos especiales, bodas, sesiones fotográficas, etc.'],
            ['name_prizecates' => 'Manicura y pedicura', 'slug_prizecates' => 'manicura-pedicura', 'description_prizecates' => 'Cuidado de uñas, esmaltado, aplicación de gel, acrílico, etc.'],
            ['name_prizecates' => 'Depilación', 'slug_prizecates' => 'depilacion', 'description_prizecates' => 'Eliminación del vello corporal mediante técnicas como cera, láser, depilación eléctrica, etc.'],
            ['name_prizecates' => 'Masajes y tratamientos corporales', 'slug_prizecates' => 'masajes-tratamientos-corporales', 'description_prizecates' => 'Masajes relajantes, reductores, tratamientos de envolturas, etc.'],
            ['name_prizecates' => 'Extensiones de cabello', 'slug_prizecates' => 'extensiones-cabello', 'description_prizecates' => 'Aplicación de extensiones para alargar o dar volumen al cabello'],
            ['name_prizecates' => 'Tratamientos de coloración capilar', 'slug_prizecates' => 'tratamientos-coloracion-capilar', 'description_prizecates' => 'Tintes, mechas, reflejos, decoloraciones, entre otros'],
            ['name_prizecates' => 'Tratamientos de keratina', 'slug_prizecates' => 'tratamientos-keratina', 'description_prizecates' => 'Tratamientos para alisar y suavizar el cabello, reduciendo el frizz y aumentando el brillo'],
            ['name_prizecates' => 'Microblading de cejas', 'slug_prizecates' => 'microblading-cejas', 'description_prizecates' => 'Técnica semipermanente de pigmentación de cejas para mejorar su forma y densidad'],
            ['name_prizecates' => 'Tratamientos de rejuvenecimiento facial', 'slug_prizecates' => 'tratamientos-rejuvenecimiento-facial', 'description_prizecates' => 'Tratamientos para combatir los signos del envejecimiento facial, como arrugas y flacidez'],
            ['name_prizecates' => 'Lifting de pestañas', 'slug_prizecates' => 'lifting-pestanas', 'description_prizecates' => 'Técnica para levantar y dar forma a las pestañas naturales para lograr una apariencia más definida y abierta del ojo'],
            ['name_prizecates' => 'Tratamientos de belleza para novias', 'slug_prizecates' => 'tratamientos-belleza-novias', 'description_prizecates' => 'Paquetes de servicios especializados para novias, que incluyen maquillaje, peinado, tratamientos faciales, entre otros'],
            ['name_prizecates' => 'Extensiones de pestañas', 'slug_prizecates' => 'extensiones-pestanas', 'description_prizecates' => 'Aplicación de extensiones individuales o en grupos para alargar y dar volumen a las pestañas naturales']
        ]);

// Usuario y Wallet
$user = User::create([
    'name' => 'Master Cuentas Admin',
    'email' => 'guidoadrian97@gmail.com',
    'identificacion' => '1311883845',
    'telefono' => '0980013638',
    'fecha_nacimiento' => '1997-04-22',
    'password' => Hash::make(env('PASS_CUENTA_MASTER')),
]);

$wallet = $user->wallet()->create([
    'wallets_name' => 'Master_Cuentas_Admin',
    'ganancias' => 0,
    'transaccitions' => 0,
]);

// Cuentas
$wallet->cuentas()->createMany([
    ['cuenta' => $request->identificacion . "001", 'tiposcuenta_id' => 4],
    ['cuenta' => $request->identificacion . "002", 'tiposcuenta_id' => 5],
]);

// Nivel
$level = level_user_referrals::create([
    'level' => '0',
    'slug' => 'cero',
]);

// Referral y Código de Referido
$user->referrals()->create([
    'referred_user_id' => $user->id,
    'level_user_referral_id' => $level->id,
]);

$user->referralcode()->create([
    "code" => 'MasterCode_1311883845',
])->principalreferral()->create([
    "estado" => true,
]);
    }


}
