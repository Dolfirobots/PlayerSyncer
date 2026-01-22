import clsx from 'clsx';
import Heading from '@theme/Heading';
import styles from './styles.module.css';

const FeatureList = [
  {
    title: 'Cross-Server Sync',
    Img: 'https://placehold.co/400x400/0098ff/white?text=Sync',
    description: (
      <>
        Keep player data, inventories, and stats synchronized across all your Minecraft servers in real-time.
      </>
    ),
  },
  {
    title: 'Flexible & Configurable',
    Img: 'https://placehold.co/400x400/25c2a0/white?text=Config',
    description: (
      <>
        Fine-tune what gets synchronized. Our powerful configuration allows you to choose exactly what data to share between servers.
      </>
    ),
  },
  {
    title: 'Built for Performance',
    Img: 'https://placehold.co/400x400/ffc000/white?text=Fast',
    description: (
      <>
        Designed to be lightweight and efficient, PlayerSyncer won't slow down your servers, ensuring a smooth experience for your players.
      </>
    ),
  },
];

function Feature({Img, title, description, index}) {
  return (
    <div className={clsx('col col--4')}>
      <div
        className={styles.featureCard}
        style={{animationDelay: `${index * 0.2}s`}}
      >
        <div className="text--center">
          <img src={Img} className={styles.featureSvg} alt={title} />
        </div>
        <div className="text--center padding-horiz--md">
          <Heading as="h3">{title}</Heading>
          <p>{description}</p>
        </div>
      </div>
    </div>
  );
}

export default function HomepageFeatures() {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} index={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
