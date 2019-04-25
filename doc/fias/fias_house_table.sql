CREATE TABLE `d_fias_house` (
  `postalcode` char(6) NOT NULL COMMENT 'Почтовый индекс',
  `ifnsfl` varchar(4) NOT NULL COMMENT 'Код ИФНС ФЛ',
  `terrifnsfl` varchar(4) NOT NULL COMMENT 'Код территориального участка ИФНС ФЛ',
  `ifnsul` varchar(4) NOT NULL COMMENT 'Код ИФНС ЮЛ',
  `terrifnsul` varchar(4) NOT NULL COMMENT 'Код территориального участка ИФНС ЮЛ',
  `okato` varchar(11) NOT NULL COMMENT 'ОКАТО',
  `oktmo` varchar(8) NOT NULL COMMENT 'ОКTMO',
  `updatedate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT 'Дата время внесения записи',

  `housenum` varchar(20) NOT NULL COMMENT 'Номер дома',

  `eststatus` int(10) unsigned NOT NULL COMMENT 'Признак владения',
  `buildnum` varchar(10) NOT NULL COMMENT 'Номер корпуса',
  `strucnum` varchar(10) NOT NULL COMMENT 'Номер строения',
  `strstatus` int(10) unsigned NOT NULL COMMENT 'Признак строения',
  `houseid` char(36) NOT NULL COMMENT 'Уникальный идентификатор записи дома',
  `houseguid` varchar(36) NOT NULL COMMENT 'Глобальный уникальный идентификатор дома',
  `aoguid` varchar(36) NOT NULL COMMENT 'Guid записи родительского объекта',
  `startdate` date NOT NULL COMMENT 'Начало действия записи',
  `enddate` date NOT NULL COMMENT 'Окончание действия записи',
  `statstatus` int(10) unsigned NOT NULL COMMENT 'Состояние дома',
  `normdoc` varchar(36) NOT NULL COMMENT 'Внешний ключ на нормативный документ',
  `counter` int(10) unsigned NOT NULL COMMENT 'Счетчик записей домов для КЛАДР 4',
  PRIMARY KEY (`houseid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='Сведения по номерам домов улиц городов и населенных пунктов,'