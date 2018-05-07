--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.13
-- Dumped by pg_dump version 9.4.0
-- Started on 2018-05-04 18:42:46

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- TOC entry 62 (class 2615 OID 1185452)
-- Name: jvue; Type: SCHEMA; Schema: -; Owner: -
--

CREATE SCHEMA jvue;


--
-- TOC entry 5494 (class 0 OID 0)
-- Dependencies: 62
-- Name: SCHEMA jvue; Type: COMMENT; Schema: -; Owner: -
--

COMMENT ON SCHEMA jvue IS '提供新的授权功能';


SET search_path = jvue, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 1056 (class 1259 OID 1185455)
-- Name: jvue_api; Type: TABLE; Schema: jvue; Owner: -; Tablespace: 
--

CREATE TABLE jvue_api (
    id integer NOT NULL,
    page_id integer,
    api_code integer,
    name character varying(64)
);


--
-- TOC entry 5495 (class 0 OID 0)
-- Dependencies: 1056
-- Name: TABLE jvue_api; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON TABLE jvue_api IS '画面接口';


--
-- TOC entry 5496 (class 0 OID 0)
-- Dependencies: 1056
-- Name: COLUMN jvue_api.id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_api.id IS 'ID';


--
-- TOC entry 5497 (class 0 OID 0)
-- Dependencies: 1056
-- Name: COLUMN jvue_api.page_id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_api.page_id IS '画面ID';


--
-- TOC entry 5498 (class 0 OID 0)
-- Dependencies: 1056
-- Name: COLUMN jvue_api.api_code; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_api.api_code IS '接口编码';


--
-- TOC entry 5499 (class 0 OID 0)
-- Dependencies: 1056
-- Name: COLUMN jvue_api.name; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_api.name IS '画面接口名';


--
-- TOC entry 1055 (class 1259 OID 1185453)
-- Name: jvue_api_id_seq; Type: SEQUENCE; Schema: jvue; Owner: -
--

CREATE SEQUENCE jvue_api_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 5500 (class 0 OID 0)
-- Dependencies: 1055
-- Name: jvue_api_id_seq; Type: SEQUENCE OWNED BY; Schema: jvue; Owner: -
--

ALTER SEQUENCE jvue_api_id_seq OWNED BY jvue_api.id;


--
-- TOC entry 1058 (class 1259 OID 1185474)
-- Name: jvue_module; Type: TABLE; Schema: jvue; Owner: -; Tablespace: 
--

CREATE TABLE jvue_module (
    id integer NOT NULL,
    name character varying(64),
    enabled integer
);


--
-- TOC entry 5501 (class 0 OID 0)
-- Dependencies: 1058
-- Name: TABLE jvue_module; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON TABLE jvue_module IS '模块';


--
-- TOC entry 5502 (class 0 OID 0)
-- Dependencies: 1058
-- Name: COLUMN jvue_module.id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_module.id IS 'ID';


--
-- TOC entry 5503 (class 0 OID 0)
-- Dependencies: 1058
-- Name: COLUMN jvue_module.name; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_module.name IS '模块名';


--
-- TOC entry 5504 (class 0 OID 0)
-- Dependencies: 1058
-- Name: COLUMN jvue_module.enabled; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_module.enabled IS '是否有效';


--
-- TOC entry 1057 (class 1259 OID 1185472)
-- Name: jvue_module_id_seq; Type: SEQUENCE; Schema: jvue; Owner: -
--

CREATE SEQUENCE jvue_module_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 5505 (class 0 OID 0)
-- Dependencies: 1057
-- Name: jvue_module_id_seq; Type: SEQUENCE OWNED BY; Schema: jvue; Owner: -
--

ALTER SEQUENCE jvue_module_id_seq OWNED BY jvue_module.id;


--
-- TOC entry 1061 (class 1259 OID 1185504)
-- Name: jvue_page; Type: TABLE; Schema: jvue; Owner: -; Tablespace: 
--

CREATE TABLE jvue_page (
    id integer NOT NULL,
    module_id integer,
    path character varying(255),
    component character varying(64),
    name character varying(64),
    icon_class character varying(64),
    parent_id integer,
    enabled integer DEFAULT 1 NOT NULL,
    show_nav integer,
    type integer
);


--
-- TOC entry 5506 (class 0 OID 0)
-- Dependencies: 1061
-- Name: TABLE jvue_page; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON TABLE jvue_page IS '画面菜单';


--
-- TOC entry 5507 (class 0 OID 0)
-- Dependencies: 1061
-- Name: COLUMN jvue_page.id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_page.id IS 'ID';


--
-- TOC entry 5508 (class 0 OID 0)
-- Dependencies: 1061
-- Name: COLUMN jvue_page.module_id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_page.module_id IS '模块ID';


--
-- TOC entry 5509 (class 0 OID 0)
-- Dependencies: 1061
-- Name: COLUMN jvue_page.path; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_page.path IS '访问路径';


--
-- TOC entry 5510 (class 0 OID 0)
-- Dependencies: 1061
-- Name: COLUMN jvue_page.component; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_page.component IS '页面模块';


--
-- TOC entry 5511 (class 0 OID 0)
-- Dependencies: 1061
-- Name: COLUMN jvue_page.name; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_page.name IS '画面名';


--
-- TOC entry 5512 (class 0 OID 0)
-- Dependencies: 1061
-- Name: COLUMN jvue_page.icon_class; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_page.icon_class IS '图标';


--
-- TOC entry 5513 (class 0 OID 0)
-- Dependencies: 1061
-- Name: COLUMN jvue_page.parent_id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_page.parent_id IS '父画面ID';


--
-- TOC entry 5514 (class 0 OID 0)
-- Dependencies: 1061
-- Name: COLUMN jvue_page.enabled; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_page.enabled IS '是否有效';


--
-- TOC entry 5515 (class 0 OID 0)
-- Dependencies: 1061
-- Name: COLUMN jvue_page.show_nav; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_page.show_nav IS '菜单里显示';


--
-- TOC entry 5516 (class 0 OID 0)
-- Dependencies: 1061
-- Name: COLUMN jvue_page.type; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_page.type IS '画面类型';


--
-- TOC entry 1060 (class 1259 OID 1185502)
-- Name: jvue_page_id_seq; Type: SEQUENCE; Schema: jvue; Owner: -
--

CREATE SEQUENCE jvue_page_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 5517 (class 0 OID 0)
-- Dependencies: 1060
-- Name: jvue_page_id_seq; Type: SEQUENCE OWNED BY; Schema: jvue; Owner: -
--

ALTER SEQUENCE jvue_page_id_seq OWNED BY jvue_page.id;


--
-- TOC entry 1126 (class 1259 OID 1192157)
-- Name: jvue_role; Type: TABLE; Schema: jvue; Owner: -; Tablespace: 
--

CREATE TABLE jvue_role (
    id integer NOT NULL,
    name character varying(64),
    enabled integer DEFAULT 1 NOT NULL
);


--
-- TOC entry 5518 (class 0 OID 0)
-- Dependencies: 1126
-- Name: TABLE jvue_role; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON TABLE jvue_role IS '角色';


--
-- TOC entry 5519 (class 0 OID 0)
-- Dependencies: 1126
-- Name: COLUMN jvue_role.id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_role.id IS 'ID';


--
-- TOC entry 5520 (class 0 OID 0)
-- Dependencies: 1126
-- Name: COLUMN jvue_role.name; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_role.name IS '角色名';


--
-- TOC entry 5521 (class 0 OID 0)
-- Dependencies: 1126
-- Name: COLUMN jvue_role.enabled; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_role.enabled IS '是否启用';


--
-- TOC entry 1059 (class 1259 OID 1185486)
-- Name: jvue_role_api; Type: TABLE; Schema: jvue; Owner: -; Tablespace: 
--

CREATE TABLE jvue_role_api (
    id bigint,
    role_id integer NOT NULL,
    api_id integer NOT NULL
);


--
-- TOC entry 5522 (class 0 OID 0)
-- Dependencies: 1059
-- Name: TABLE jvue_role_api; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON TABLE jvue_role_api IS '角色对应接口权限';


--
-- TOC entry 5523 (class 0 OID 0)
-- Dependencies: 1059
-- Name: COLUMN jvue_role_api.id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_role_api.id IS 'ID';


--
-- TOC entry 5524 (class 0 OID 0)
-- Dependencies: 1059
-- Name: COLUMN jvue_role_api.role_id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_role_api.role_id IS '角色ID';


--
-- TOC entry 5525 (class 0 OID 0)
-- Dependencies: 1059
-- Name: COLUMN jvue_role_api.api_id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_role_api.api_id IS '接口ID';


--
-- TOC entry 1125 (class 1259 OID 1192155)
-- Name: jvue_role_id_seq; Type: SEQUENCE; Schema: jvue; Owner: -
--

CREATE SEQUENCE jvue_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 5526 (class 0 OID 0)
-- Dependencies: 1125
-- Name: jvue_role_id_seq; Type: SEQUENCE OWNED BY; Schema: jvue; Owner: -
--

ALTER SEQUENCE jvue_role_id_seq OWNED BY jvue_role.id;


--
-- TOC entry 1127 (class 1259 OID 1192189)
-- Name: jvue_role_page; Type: TABLE; Schema: jvue; Owner: -; Tablespace: 
--

CREATE TABLE jvue_role_page (
    id bigint,
    role_id integer NOT NULL,
    page_id integer NOT NULL
);


--
-- TOC entry 5527 (class 0 OID 0)
-- Dependencies: 1127
-- Name: TABLE jvue_role_page; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON TABLE jvue_role_page IS '角色对应画面';


--
-- TOC entry 5528 (class 0 OID 0)
-- Dependencies: 1127
-- Name: COLUMN jvue_role_page.id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_role_page.id IS '角色对应画面';


--
-- TOC entry 5529 (class 0 OID 0)
-- Dependencies: 1127
-- Name: COLUMN jvue_role_page.role_id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_role_page.role_id IS '角色ID';


--
-- TOC entry 5530 (class 0 OID 0)
-- Dependencies: 1127
-- Name: COLUMN jvue_role_page.page_id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_role_page.page_id IS '画面ID';


--
-- TOC entry 1063 (class 1259 OID 1185513)
-- Name: jvue_role_segment; Type: TABLE; Schema: jvue; Owner: -; Tablespace: 
--

CREATE TABLE jvue_role_segment (
    id bigint,
    role_id integer NOT NULL,
    segment_id integer NOT NULL
);


--
-- TOC entry 5531 (class 0 OID 0)
-- Dependencies: 1063
-- Name: TABLE jvue_role_segment; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON TABLE jvue_role_segment IS '角色对应画面片段';


--
-- TOC entry 5532 (class 0 OID 0)
-- Dependencies: 1063
-- Name: COLUMN jvue_role_segment.id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_role_segment.id IS 'ID';


--
-- TOC entry 5533 (class 0 OID 0)
-- Dependencies: 1063
-- Name: COLUMN jvue_role_segment.role_id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_role_segment.role_id IS '角色ID';


--
-- TOC entry 5534 (class 0 OID 0)
-- Dependencies: 1063
-- Name: COLUMN jvue_role_segment.segment_id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_role_segment.segment_id IS '画面片段ID';


--
-- TOC entry 1062 (class 1259 OID 1185511)
-- Name: jvue_role_segment_id_seq; Type: SEQUENCE; Schema: jvue; Owner: -
--

CREATE SEQUENCE jvue_role_segment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 5535 (class 0 OID 0)
-- Dependencies: 1062
-- Name: jvue_role_segment_id_seq; Type: SEQUENCE OWNED BY; Schema: jvue; Owner: -
--

ALTER SEQUENCE jvue_role_segment_id_seq OWNED BY jvue_role_segment.id;


--
-- TOC entry 1065 (class 1259 OID 1185521)
-- Name: jvue_segment; Type: TABLE; Schema: jvue; Owner: -; Tablespace: 
--

CREATE TABLE jvue_segment (
    id integer NOT NULL,
    page_id integer,
    segment_code integer,
    name character varying(64)
);


--
-- TOC entry 5536 (class 0 OID 0)
-- Dependencies: 1065
-- Name: TABLE jvue_segment; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON TABLE jvue_segment IS '画面片断';


--
-- TOC entry 5537 (class 0 OID 0)
-- Dependencies: 1065
-- Name: COLUMN jvue_segment.id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_segment.id IS 'ID';


--
-- TOC entry 5538 (class 0 OID 0)
-- Dependencies: 1065
-- Name: COLUMN jvue_segment.page_id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_segment.page_id IS '画面ID';


--
-- TOC entry 5539 (class 0 OID 0)
-- Dependencies: 1065
-- Name: COLUMN jvue_segment.segment_code; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_segment.segment_code IS '片段ID，画面内唯一';


--
-- TOC entry 5540 (class 0 OID 0)
-- Dependencies: 1065
-- Name: COLUMN jvue_segment.name; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_segment.name IS '画面片段名';


--
-- TOC entry 1064 (class 1259 OID 1185519)
-- Name: jvue_segment_id_seq; Type: SEQUENCE; Schema: jvue; Owner: -
--

CREATE SEQUENCE jvue_segment_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 5541 (class 0 OID 0)
-- Dependencies: 1064
-- Name: jvue_segment_id_seq; Type: SEQUENCE OWNED BY; Schema: jvue; Owner: -
--

ALTER SEQUENCE jvue_segment_id_seq OWNED BY jvue_segment.id;


--
-- TOC entry 1122 (class 1259 OID 1192054)
-- Name: jvue_user; Type: TABLE; Schema: jvue; Owner: -; Tablespace: 
--

CREATE TABLE jvue_user (
    id bigint NOT NULL,
    username character varying(64),
    password character varying(128),
    email character varying(128),
    status integer,
    nickname character varying(64),
    super_user integer
);


--
-- TOC entry 5542 (class 0 OID 0)
-- Dependencies: 1122
-- Name: TABLE jvue_user; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON TABLE jvue_user IS '用户';


--
-- TOC entry 5543 (class 0 OID 0)
-- Dependencies: 1122
-- Name: COLUMN jvue_user.id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_user.id IS '用户ID';


--
-- TOC entry 5544 (class 0 OID 0)
-- Dependencies: 1122
-- Name: COLUMN jvue_user.username; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_user.username IS '用户名';


--
-- TOC entry 5545 (class 0 OID 0)
-- Dependencies: 1122
-- Name: COLUMN jvue_user.password; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_user.password IS '密码';


--
-- TOC entry 5546 (class 0 OID 0)
-- Dependencies: 1122
-- Name: COLUMN jvue_user.email; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_user.email IS '邮箱';


--
-- TOC entry 5547 (class 0 OID 0)
-- Dependencies: 1122
-- Name: COLUMN jvue_user.status; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_user.status IS '状态';


--
-- TOC entry 5548 (class 0 OID 0)
-- Dependencies: 1122
-- Name: COLUMN jvue_user.nickname; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_user.nickname IS '昵称';


--
-- TOC entry 5549 (class 0 OID 0)
-- Dependencies: 1122
-- Name: COLUMN jvue_user.super_user; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_user.super_user IS '超级用户';


--
-- TOC entry 1121 (class 1259 OID 1192052)
-- Name: jvue_user_id_seq; Type: SEQUENCE; Schema: jvue; Owner: -
--

CREATE SEQUENCE jvue_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 5550 (class 0 OID 0)
-- Dependencies: 1121
-- Name: jvue_user_id_seq; Type: SEQUENCE OWNED BY; Schema: jvue; Owner: -
--

ALTER SEQUENCE jvue_user_id_seq OWNED BY jvue_user.id;


--
-- TOC entry 1067 (class 1259 OID 1185529)
-- Name: jvue_user_role; Type: TABLE; Schema: jvue; Owner: -; Tablespace: 
--

CREATE TABLE jvue_user_role (
    id bigint NOT NULL,
    user_id bigint,
    role_id integer
);


--
-- TOC entry 5551 (class 0 OID 0)
-- Dependencies: 1067
-- Name: TABLE jvue_user_role; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON TABLE jvue_user_role IS '用户权限';


--
-- TOC entry 5552 (class 0 OID 0)
-- Dependencies: 1067
-- Name: COLUMN jvue_user_role.id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_user_role.id IS 'ID';


--
-- TOC entry 5553 (class 0 OID 0)
-- Dependencies: 1067
-- Name: COLUMN jvue_user_role.user_id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_user_role.user_id IS '用户ID';


--
-- TOC entry 5554 (class 0 OID 0)
-- Dependencies: 1067
-- Name: COLUMN jvue_user_role.role_id; Type: COMMENT; Schema: jvue; Owner: -
--

COMMENT ON COLUMN jvue_user_role.role_id IS '角色ID';


--
-- TOC entry 1066 (class 1259 OID 1185527)
-- Name: jvue_user_role_id_seq; Type: SEQUENCE; Schema: jvue; Owner: -
--

CREATE SEQUENCE jvue_user_role_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 5555 (class 0 OID 0)
-- Dependencies: 1066
-- Name: jvue_user_role_id_seq; Type: SEQUENCE OWNED BY; Schema: jvue; Owner: -
--

ALTER SEQUENCE jvue_user_role_id_seq OWNED BY jvue_user_role.id;


--
-- TOC entry 5335 (class 2604 OID 1185458)
-- Name: id; Type: DEFAULT; Schema: jvue; Owner: -
--

ALTER TABLE ONLY jvue_api ALTER COLUMN id SET DEFAULT nextval('jvue_api_id_seq'::regclass);


--
-- TOC entry 5336 (class 2604 OID 1185477)
-- Name: id; Type: DEFAULT; Schema: jvue; Owner: -
--

ALTER TABLE ONLY jvue_module ALTER COLUMN id SET DEFAULT nextval('jvue_module_id_seq'::regclass);


--
-- TOC entry 5337 (class 2604 OID 1185507)
-- Name: id; Type: DEFAULT; Schema: jvue; Owner: -
--

ALTER TABLE ONLY jvue_page ALTER COLUMN id SET DEFAULT nextval('jvue_page_id_seq'::regclass);


--
-- TOC entry 5342 (class 2604 OID 1192160)
-- Name: id; Type: DEFAULT; Schema: jvue; Owner: -
--

ALTER TABLE ONLY jvue_role ALTER COLUMN id SET DEFAULT nextval('jvue_role_id_seq'::regclass);


--
-- TOC entry 5339 (class 2604 OID 1185524)
-- Name: id; Type: DEFAULT; Schema: jvue; Owner: -
--

ALTER TABLE ONLY jvue_segment ALTER COLUMN id SET DEFAULT nextval('jvue_segment_id_seq'::regclass);


--
-- TOC entry 5341 (class 2604 OID 1192057)
-- Name: id; Type: DEFAULT; Schema: jvue; Owner: -
--

ALTER TABLE ONLY jvue_user ALTER COLUMN id SET DEFAULT nextval('jvue_user_id_seq'::regclass);


--
-- TOC entry 5340 (class 2604 OID 1185532)
-- Name: id; Type: DEFAULT; Schema: jvue; Owner: -
--

ALTER TABLE ONLY jvue_user_role ALTER COLUMN id SET DEFAULT nextval('jvue_user_role_id_seq'::regclass);


--
-- TOC entry 5345 (class 2606 OID 1185462)
-- Name: jvue_api_menu_id_api_id_key; Type: CONSTRAINT; Schema: jvue; Owner: -; Tablespace: 
--

ALTER TABLE ONLY jvue_api
    ADD CONSTRAINT jvue_api_menu_id_api_id_key UNIQUE (page_id, api_code);


--
-- TOC entry 5347 (class 2606 OID 1185460)
-- Name: jvue_api_pkey; Type: CONSTRAINT; Schema: jvue; Owner: -; Tablespace: 
--

ALTER TABLE ONLY jvue_api
    ADD CONSTRAINT jvue_api_pkey PRIMARY KEY (id);


--
-- TOC entry 5353 (class 2606 OID 1185510)
-- Name: jvue_menu_pkey; Type: CONSTRAINT; Schema: jvue; Owner: -; Tablespace: 
--

ALTER TABLE ONLY jvue_page
    ADD CONSTRAINT jvue_menu_pkey PRIMARY KEY (id);


--
-- TOC entry 5349 (class 2606 OID 1185479)
-- Name: jvue_module_pkey; Type: CONSTRAINT; Schema: jvue; Owner: -; Tablespace: 
--

ALTER TABLE ONLY jvue_module
    ADD CONSTRAINT jvue_module_pkey PRIMARY KEY (id);


--
-- TOC entry 5351 (class 2606 OID 1192184)
-- Name: jvue_role_api_pkey; Type: CONSTRAINT; Schema: jvue; Owner: -; Tablespace: 
--

ALTER TABLE ONLY jvue_role_api
    ADD CONSTRAINT jvue_role_api_pkey PRIMARY KEY (role_id, api_id);


--
-- TOC entry 5365 (class 2606 OID 1192194)
-- Name: jvue_role_page_pkey; Type: CONSTRAINT; Schema: jvue; Owner: -; Tablespace: 
--

ALTER TABLE ONLY jvue_role_page
    ADD CONSTRAINT jvue_role_page_pkey PRIMARY KEY (role_id, page_id);


--
-- TOC entry 5363 (class 2606 OID 1192163)
-- Name: jvue_role_pkey; Type: CONSTRAINT; Schema: jvue; Owner: -; Tablespace: 
--

ALTER TABLE ONLY jvue_role
    ADD CONSTRAINT jvue_role_pkey PRIMARY KEY (id);


--
-- TOC entry 5355 (class 2606 OID 1192188)
-- Name: jvue_role_segment_pkey; Type: CONSTRAINT; Schema: jvue; Owner: -; Tablespace: 
--

ALTER TABLE ONLY jvue_role_segment
    ADD CONSTRAINT jvue_role_segment_pkey PRIMARY KEY (role_id, segment_id);


--
-- TOC entry 5357 (class 2606 OID 1185526)
-- Name: jvue_segment_pkey; Type: CONSTRAINT; Schema: jvue; Owner: -; Tablespace: 
--

ALTER TABLE ONLY jvue_segment
    ADD CONSTRAINT jvue_segment_pkey PRIMARY KEY (id);


--
-- TOC entry 5361 (class 2606 OID 1192059)
-- Name: jvue_user_pkey; Type: CONSTRAINT; Schema: jvue; Owner: -; Tablespace: 
--

ALTER TABLE ONLY jvue_user
    ADD CONSTRAINT jvue_user_pkey PRIMARY KEY (id);


--
-- TOC entry 5359 (class 2606 OID 1185534)
-- Name: jvue_user_role_pkey; Type: CONSTRAINT; Schema: jvue; Owner: -; Tablespace: 
--

ALTER TABLE ONLY jvue_user_role
    ADD CONSTRAINT jvue_user_role_pkey PRIMARY KEY (id);


-- Completed on 2018-05-04 18:42:46

--
-- PostgreSQL database dump complete
--

